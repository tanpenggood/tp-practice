package com.itplh.thread.demo1;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-19 20:19
 * @version: v1.0.0
 */
@ThreadSafe
public class ThreadSafeTest {
    public static volatile LongAdder count = new LongAdder();
    public static final int loop = 100;

    public static void main(String[] args) {
        new Thread(() -> add(), "consumer1").start();
        new Thread(() -> add(), "consumer2").start();
    }

    @ThreadSafe
    private static void add() {
        for (int i = 0; i < loop; i++) {
            count.increment();
            // 模拟计算耗时
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("%s %s", Thread.currentThread().getName(), count.longValue()));
    }
}
