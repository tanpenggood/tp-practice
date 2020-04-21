package com.itplh;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-21 22:20
 * @version: v1.0.0
 */
public class SimpleBIO {

    private static final File DEFAULT_FILE = new File("./bio-simple.txt");

    public static void main(String[] args) throws Exception {
        SimpleBIO simpleBIO = new SimpleBIO();

        simpleBIO.write();
        simpleBIO.write();

        simpleBIO.write(null, "\nhello 1", true);
        simpleBIO.write(null, "\nhello 2", true);

        simpleBIO.write(new File("./bio-custom.txt"), null, false);
        simpleBIO.write(new File("./bio-custom.txt"), null, false);
        simpleBIO.write(new File("./bio-custom.txt"), "\nhello custom file 1", true);
        simpleBIO.write(new File("./bio-custom.txt"), "\nhello custom file 2", true);

        simpleBIO.copyTxt(DEFAULT_FILE, new File("./bio-copy-txt1.txt"));
        simpleBIO.copyTxt(new File("./bio-custom.txt"), new File("./bio-copy-txt2.txt"));

        simpleBIO.copy(DEFAULT_FILE, new File("./bio-copy1.txt"));
        simpleBIO.copy(new File("./bio-custom.txt"), new File("./bio-copy2.txt"));

//        simpleBIO.copyTxt(new File("./WechatIMG1.jpeg") , new File("./copy-txt.jpeg"));
//        simpleBIO.copy(new File("./WechatIMG1.jpeg") , new File("./copy.jpeg"));
    }

    /**
     * @param file   指定写入数据的文件 若为null，则写入默认文件
     * @param text   写入的数据 若为null，则写入默认值
     * @param append 是否为追加写入
     * @description: BIO写数据
     * @author: tanpeng
     * @date : 2020/4/21 17:21
     * @version: v1.0.0
     */
    private void write(File file, String text, boolean append) {
        // 1.设置默认值
        file = Optional.ofNullable(file).orElse(DEFAULT_FILE);
        text = Optional.ofNullable(text).orElseGet(() -> "\nhello BIO. " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒", Locale.CHINA)));
        try (
                // 2.获取文件输出流，自动关流
                FileOutputStream out = new FileOutputStream(file, append);
        ) {
            // 3.写数据
            out.write(text.getBytes("UTF-8"));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.打印最新数据
            try {
                System.out.println(String.format("-------------------%s-----------------", file.getName()));
                System.out.println(read(file));
                System.out.println();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    private void write() {
        write(null, null, false);
    }

    /**
     * @description: BIO读数据
     * @author: tanpeng
     * @date : 2020/4/21 17:20
     * @version: v1.0.0
     */
    private String read(File file) throws UnsupportedEncodingException {
        StringBuilder text = new StringBuilder();
        try (
                // 1.获取文件输入流，自动关流
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        ) {
            // 2.读数据
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 4.返回数据
        return text.toString();
    }

    /**
     * @description: BIO复制纯文本文件
     * @author: tanpeng
     * @date : 2020/4/21 17:37
     * @version: v1.0.0
     */
    private void copyTxt(File source, File dest) {
        try {
            write(dest, read(source), false);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @description: BIO复制文件
     * @author: tanpeng
     * @date : 2020/4/21 17:44
     * @version: v1.0.0
     */
    private void copy(File source, File dest) {
        try (
                // 1.获取输入、输出流，自动关流
                BufferedInputStream sourceIn = new BufferedInputStream(new FileInputStream(source), 8092);
                BufferedOutputStream destOut = new BufferedOutputStream(new FileOutputStream(dest), 8092);
        ) {
            // 2.从source文件读数据
            byte[] data = new byte[8092];
            int pointer = -1;
            while ((pointer = sourceIn.read(data)) != -1) {
                // 3.写数据到dest文件
                destOut.write(data, 0, pointer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
