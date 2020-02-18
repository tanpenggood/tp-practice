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
        //boolean exists = Files.exists(Paths.get(first, more));
        boolean exists = Paths.get(first, more).toFile().exists();
        return exists;
    }

    public static void createDirectories(String first, String... more) {
        try {
            Files.createDirectories(Paths.get(first, more));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void forceDelete(String first, String... more) {
        CommandExecutor.executor.execute("rd /s /q " + Paths.get(first, more).toString());
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

}
