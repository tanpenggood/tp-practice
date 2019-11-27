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

        // 1.forEach
        Stream.of(array).forEach(System.out::print);
        System.out.println();

        // 2.filter
        Stream.of(array).filter(str -> str.length() > 2).forEach(System.out::print);
        System.out.println();

        // 3.map
        Stream.of(array).map(str -> 10 + str).forEach(System.out::print);
        System.out.println();

        // 4.count
        System.out.print(Stream.of(array).count());
        System.out.println();

        // 5.limit
        Stream.of(array).limit(5).forEach(System.out::print);
        System.out.println();

        // 6.skip
        Stream.of(array).skip(5).forEach(System.out::print);
        System.out.println();

        // 7.concat
        Stream<String> stream1 = Stream.of("java ", "c ", "python ");
        Stream<String> stream2 = Stream.of("android ", "ios ");
        Stream.concat(stream1, stream2).forEach(System.out::print);
        System.out.println();

    }
}