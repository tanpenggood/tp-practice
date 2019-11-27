package com.itplh.demo6.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * @description: 获取Stream流的两种方式
 * 注意：stream不能重复消费 即，只能被消费(使用)一次
 * 如果重复消费，stream has already been operated upon or closed
 * @author: tanpeng
 * @date: 2019-11-27 23:00
 * @version: v1.0.0
 */
public class Demo1StreamTest {

    public static void main(String[] args) {
        // 方式一：Connection接口的stream方法获取Stream
        Collection<String> collection = new ArrayList<>();
        Stream<String> stream1 = collection.stream();

        // 方式二：使用Stream接口的of静态方法获取Stream
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5, 6);
    }
}
