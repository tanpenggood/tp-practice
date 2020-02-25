package com.itplh.util;

import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.File;
import java.io.InputStream;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-07 09:57
 * @version: v1.0.0
 */
public class TestFileUtil {

    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static String getPath() {
        return TestFileUtil.class.getResource("/").getPath();
    }

    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }

    public static void main(String[] args) {
        System.out.println(ExcelTypeEnum.XLSX.getValue().equals(".xlsx"));
    }
}
