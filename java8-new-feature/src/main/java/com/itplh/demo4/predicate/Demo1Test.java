package com.itplh.demo4.predicate;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * @description:
 * 从String[] stars = {"迪丽热巴,女", "赵丽颖,女", "玛尔扎哈,男", "古力娜扎,女"};中获取数据
 * 需求：
 *  1、性别为女
 *  2、名字为4个字
 *
 * @author: tanpeng
 * @date: 2019-11-24 12:59
 * @version: v1.0.0
 */
public class Demo1Test {

    private static boolean filter(String star, Predicate<String> predicate1, Predicate<String> predicate2) {
        return predicate1.and(predicate2).test(star);
    }

    public static void main(String[] args) {
        ArrayList<String> targetStars = new ArrayList<>();
        String[] stars = {"迪丽热巴,女", "赵丽颖,女", "玛尔扎哈,男", "古力娜扎,女"};
        for (String star : stars) {
            boolean flag = filter(star,
                    str -> "女".equals(str.split(",")[1]),
                    str -> str.split(",")[0].length() == 4);
            if (flag) {
                targetStars.add(star);
            }
        }
        targetStars.forEach(System.out::println);
    }
}
