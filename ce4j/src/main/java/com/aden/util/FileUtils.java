package com.aden.util;

import com.aden.command.CommandExecutor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-18 11:50
 * @version: 1.0.0
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

    /**
     * @description: 删除文件／目录
     * @author: tanpeng
     * @date : 2020-02-19 12:04
     * @version: v1.0.0
     */
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

    public static void createDirectoriesIfExistClean(String rootDir, String projectParentDir, String projectDir) {
        if (FileUtils.exists(rootDir, projectParentDir)) {
            if (FileUtils.exists(rootDir, projectParentDir, projectDir)) {
                FileUtils.forceDelete(rootDir, projectParentDir, projectDir);
            }
        } else {
            FileUtils.createDirectories(rootDir, projectParentDir);
        }
    }

    public static void copy(String sourceAbsPath, String targetAbsPath) {
        // cp -r /home/data/* /home/data2    复制过程中会忽略隐藏文件
        // cp -r /home/data/ /home/data2     复制过程中不会忽略隐藏文件
        // xcopy "e:/arms/data" "e:/arms/data2"  windows 复制过程中不会忽略隐藏文件，多级路径时需要使用双引号包裹
        if (isWindows()) {
            CommandExecutor.executor.execute(String.format("xcopy \"%s\" \"%s\" /s /q /i", sourceAbsPath, targetAbsPath));
        } else {
            String commandLine = new StringBuilder()
                    .append(String.format("cp -r %s/ %s", sourceAbsPath, targetAbsPath)).toString();
            CommandExecutor.executor.executeMutilShell(Arrays.asList(commandLine));
        }
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

}
