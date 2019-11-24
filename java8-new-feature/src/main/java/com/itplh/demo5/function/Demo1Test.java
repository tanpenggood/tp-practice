package com.itplh.demo5.function;

import java.util.function.Function;

/**
 * @description:
 *  使用Function进行函数模型的拼接，String str = "赵丽颖,20";
 *  需求：
 *      1、截取年龄部分，得到字符串
 *          Function<String, String> "赵丽颖,20" -> "20"
 *      2、将1的结果转为Integer
 *          Function<String, Integer> "20" -> 20
 *      3、将2的结果加10，得到字符串
 *          Function<Integer, String> 20 + 10 -> "30"
 * @author: tanpeng
 * @date: 2019-11-24 14:31
 * @version: v1.0.0
 */
public class Demo1Test {

    /**
     * @description:
     * String -> String
     * String -> Integer
     * Integer -> String
     *
     * @author: tanpeng
     * @date : 2019-11-24 14:38
     * @version: v1.0.0
     * @param str 1
     * @param function1 2
     * @param function2 3
     * @param function3 4
     * @return : java.lang.String
     */
    private static String getAge(String str,
                                 Function<String, String> function1,
                                 Function<String, Integer> function2,
                                 Function<Integer, String> function3) {
        return function1.andThen(function2).andThen(function3).apply(str);
    }

    public static void main(String[] args) {
        String star = "赵丽颖,20";
        System.out.println(getAge(star,
                str -> str.split(",")[1],
                str -> Integer.parseInt(str),
                i -> i + 10 + ""));
    }
}
