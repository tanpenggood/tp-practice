package com.itplh.demo6.stream;

import java.util.stream.Stream;

/**
 * @description: Stream常用API
 * forEach（终结方法）
 * filter
 * map
 * count（终结方法）
 * limit
 * skip
 * concat
 * @author: tanpeng
 * @date: 2019-11-27 23:18
 * @version: v1.0.0
 */
public class Demo2StreamApiTest {

    public static void main(String[] args) {
        String[] array = {"1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "9 ", "10 "};

        // 1.forEach 遍历
        Stream.of(array).forEach(System.out::print);
        System.out.println();

        // 2.filter 过滤获取length大于2的元素
        Stream.of(array).filter(str -> str.length() > 2).forEach(System.out::print);
        System.out.println();

        // 3.map 在每个字符串前加上 hello：
        Stream.of(array).map(str -> "hello：" + str).forEach(System.out::print);
        System.out.println();

        // 4.count 统计元素总数
        System.out.print(Stream.of(array).count());
        System.out.println();

        // 5.limit 截取前5个元素
        Stream.of(array).limit(5).forEach(System.out::print);
        System.out.println();

        // 6.skip 跳过前5个元素
        Stream.of(array).skip(5).forEach(System.out::print);
        System.out.println();

        // 7.concat 合并两个Stream得到新的Stream
        Stream<String> stream1 = Stream.of("java ", "c ", "python ");
        Stream<String> stream2 = Stream.of("android ", "ios ");
        Stream.concat(stream1, stream2).forEach(System.out::print);
        System.out.println();

    }
}