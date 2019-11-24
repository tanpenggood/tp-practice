package com.itplh.demo3.consumer;

import java.util.function.Consumer;

/**
 * @description: 消费型接口-Consumer
 *      void accept(T t);
 *      default Consumer<T> andThen(Consumer<? super T> after) {
 *         Objects.requireNonNull(after);
 *         return (T t) -> { accept(t); after.accept(t); };
 *      }
 * @author: tanpeng
 * @date: 2019-11-24 11:58
 * @version: v1.0.0
 */
public class TestConsumer {

    private static void acceptString(String str, Consumer<String> consumer) {
        consumer.accept(str);
    }

    /**
     * @description: 顺序消费，消费链
     * @author: tanpeng
     * @date : 2019-11-24 12:47
     * @version: v1.0.0
     * @param str 1
     * @param consumer1 2
     * @param consumer2 3
     * @return : void
     */
    private static void andThenString(String str, Consumer<String> consumer1, Consumer<String> consumer2) {
        consumer1.andThen(consumer2).accept(str);
    }

    public static void main(String[] args) {
        // 消费-直接打印
        acceptString("Consumer String", str -> System.out.println(str));
        // 消费-参与运算
        acceptString("Computed String", str -> System.out.println(str.toLowerCase()));
        acceptString("Computed String", str -> System.out.println(str.toUpperCase()));

        // 消费-顺序消费
        andThenString("andThenString",
                str -> System.out.println(str),
                str -> System.out.println(new StringBuilder(str).reverse().toString()));
    }
}
