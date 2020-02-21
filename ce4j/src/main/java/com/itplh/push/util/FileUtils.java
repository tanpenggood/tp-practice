package com.itplh.push.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
            Path path = Paths.get(first, more);
            if (!path.toFile().exists()) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete file, reference easyexcel FileUtils.delete
     *
     * @param file
     */
    public static void delete(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }
            for (int i = 0; i < childFiles.length; i++) {
                delete(childFiles[i]);
            }
            file.delete();
        }
    }

    public static void createDirectoriesIfExistClean(String rootDir, String projectDir) {
        if (FileUtils.exists(rootDir)) {
            if (FileUtils.exists(rootDir, projectDir)) {
                FileUtils.delete(new File(rootDir, projectDir));
            }
        } else {
            FileUtils.createDirectories(rootDir);
        }
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

}
