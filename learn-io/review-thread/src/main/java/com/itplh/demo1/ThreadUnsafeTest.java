package com.itplh.demo1;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-19 21:59
 * @version: v1.0.0
 */
@ThreadUnsafe
public class ThreadUnsafeTest {

    public static volatile int count = 0;
    public static final int loop = 100;

    public static void main(String[] args) {
        new Thread(() -> add(), "consumer1").start();
        new Thread(() -> add(), "consumer2").start();
    }

    @ThreadUnsafe
    private static void add() {
        for (int i = 0; i < loop; i++) {
            ++count;
            // 模拟计算耗时
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("%s %s", Thread.currentThread().getName(), count));
    }
}
