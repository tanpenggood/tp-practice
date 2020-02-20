package com.itplh.push.util;

import com.aden.command.CommandExecutor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-20 23:43
 * @version: v1.0.0
 */
public class FileUtils {

    public static boolean exists(String first, String... more) {
        return Paths.get(first, more).toFile().exists();
    }

    public static void createDirectories(String first, String... more) {
        try {
            Files.createDirectories(Paths.get(first, more));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void forceDelete(String... more) {
        String filePath = String.join("/", more);
        File file = new File(filePath);
        // 删除文件
        if (file.isFile()) {
            file.delete();
            return;
        }
        // 删除目录
        if (isWindows()) {
            // 执行windows命令时 多级路径时需要使用双引号包裹
            CommandExecutor.executor.execute(String.format("rd /s /q \"%s\"", filePath));
        } else {
            CommandExecutor.executor.executeMutilShell(Arrays.asList(String.format("rm -rf %s", filePath)));
        }
    }

    public static void createDirectoriesIfExistClean(String rootDir, String projectDir) {
        if (FileUtils.exists(rootDir)) {
            if (FileUtils.exists(rootDir, projectDir)) {
                FileUtils.forceDelete(rootDir, projectDir);
            }
        } else {
            FileUtils.createDirectories(rootDir);
        }
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

}
