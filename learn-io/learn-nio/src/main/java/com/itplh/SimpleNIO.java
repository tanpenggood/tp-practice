package com.itplh;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-21 16:38
 * @version: 1.0.0
 */
public class SimpleNIO {

    private static final File DEFAULT_FILE = new File("./nio-simple.txt");

    public static void main(String[] args) throws Exception {
        SimpleNIO simpleNIO = new SimpleNIO();

        simpleNIO.write();
        simpleNIO.write();

        simpleNIO.write(null, "\nhello 1", true);
        simpleNIO.write(null, "\nhello 3",true);

        simpleNIO.write(new File("./nio-custom.txt"), null,false);
        simpleNIO.write(new File("./nio-custom.txt"), null,false);
        simpleNIO.write(new File("./nio-custom.txt"), "\nhello custom file 1",true);
        simpleNIO.write(new File("./nio-custom.txt"), "\nhello custom file 2",true);

        simpleNIO.copyTxt(DEFAULT_FILE, new File("./nio-copy-txt1.txt"));
        simpleNIO.copyTxt(new File("./nio-custom.txt") , new File("./nio-copy-txt3.txt"));

        simpleNIO.copy(DEFAULT_FILE, new File("./nio-copy1.txt"));
        simpleNIO.copy(new File("./nio-custom.txt") , new File("./nio-copy3.txt"));

//        simpleNIO.copyTxt(new File("./hd_x264.mp4") , new File("./copy-txt.mp4"));
//        simpleNIO.copy(new File("./hd_x264.mp4") , new File("./copy.mp4"));
    }

    /**
     * @description: NIO写数据
     * @author: tanpeng
     * @date : 2020/4/21 17:21
     * @version: v1.0.0
     * @param file 指定写入数据的文件 若为null，则写入默认文件
     * @param text 写入的数据 若为null，则写入默认值
     * @param append 是否为追加写入
     */
    private void write(File file, String text, boolean append) {
        // 1.设置默认值
        file = Optional.ofNullable(file).orElse(DEFAULT_FILE);
        text = Optional.ofNullable(text).orElseGet(() -> "\nhello NIO. " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒", Locale.CHINA)));
        // 2.构建ByteBuffer
        ByteBuffer buffer = Charset.forName("UTF-8").encode(text);
        try (
                // 3.获取FileChannel，自动关流
                FileChannel channel = new FileOutputStream(file, append).getChannel();
        ) {
            // 4.写数据
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 5.打印最新数据
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
     * @description: NIO读数据
     * @author: tanpeng
     * @date : 2020/4/21 17:20
     * @version: v1.0.0
     */
    private String read(File file) throws UnsupportedEncodingException {
        // 1.构建ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        try (
                // 2.获取FileChannel，自动关流
                FileChannel channel = new FileInputStream(file).getChannel();
        ) {
            // 3.读数据
            channel.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 4.返回数据
        return new String(buffer.array(), "UTF-8");
    }

    /**
     * @description: NIO复制纯文本文件
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
     * @description: NIO复制文件
     * @author: tanpeng
     * @date : 2020/4/21 17:44
     * @version: v1.0.0
     */
    private void copy(File source, File dest) {
        // 1.构建ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate((int) source.length());
        try (
                // 2.获取FileChannel，自动关流
                FileChannel sourceChannel = new FileInputStream(source).getChannel();
                FileChannel destChannel = new FileOutputStream(dest).getChannel();
        ) {
            // 3.从source文件读数据
            sourceChannel.read(buffer);
            // 4. 切换指针
            buffer.flip();
            // 5. 写数据到dest文件
            destChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
