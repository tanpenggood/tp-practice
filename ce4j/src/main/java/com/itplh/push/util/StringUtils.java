package com.itplh.push.util;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-20 23:58
 * @version: v1.0.0
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        return (str == null || "".equals(str));
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

}
