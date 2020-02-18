package com.aden.util;

import com.aden.CommandExecutor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-18 11:50
 * @version: 1.0.0
 */
public class FileUtils {

    public static boolean exists(String first, String... more) {
        // return Files.exists(Paths.get(first, more));
        return Paths.get(first, more).toFile().exists();
    }

    public static void createDirectories(String first, String... more) {
        try {
            Files.createDirectories(Paths.get(first, more));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void forceDelete(String first, String... more) {
        String delCommand = isWindows() ? "rd /s /q " : "rm -rf ";
        String deletePath = Paths.get(first, more).toString();
        CommandExecutor.executor.execute(delCommand + deletePath);
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
        String copyCommand = isWindows() ? "copy " : "cp -r";
        CommandExecutor.executor.execute(String.join(" ", copyCommand, sourceAbsPath, targetAbsPath));
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

}
