package com.itplh.juc.queue;

import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @description: 有界阻塞队列
 * @author: tanpeng
 * @date: 2020-05-17 22:45
 * @version: v1.0.0
 */
public class TestBlockQueue {

    public static void main(String[] args) {
        TestBlockQueue test = new TestBlockQueue();
        test.group1();
        test.group2();
        test.group3();
        test.group4();
    }

    /**
     * 方法类型：抛出异常
     * 插入 add(e)
     * 移除 remove()
     * 检查 element()
     *
     * @description:
     * @author: tanpeng
     * @date : 2020-05-17 22:55
     * @version: v1.0.0
     */
    private void group1() {
        BlockingQueue queue = new ArrayBlockingQueue(3);
        // provider
        IntStream.range(1, 4).forEach(i -> queue.add("product" + i));
        // consumer
        while (true) {
            try {
                System.out.format("%s %s %s\n", "group1", Thread.currentThread().getName(), queue.remove());
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }

    /**
     * 方法类型：特殊值
     * 插入 offer(e)
     * 移除 poll()
     * 检查 peek()
     *
     * @description:
     * @author: tanpeng
     * @date : 2020-05-17 22:55
     * @version: v1.0.0
     */
    private void group2() {
        BlockingQueue queue = new ArrayBlockingQueue(3);
        // provider
        IntStream.range(1, 4).forEach(i -> queue.offer("product" + i));
        // consumer
        while (queue.peek() != null) {
            System.out.format("%s %s %s\n", "group2", Thread.currentThread().getName(), queue.poll());
        }
    }

    /**
     * 方法类型：阻塞
     * 插入 put(e)
     * 移除 take()
     * 检查 不可用
     *
     * @description:
     * @author: tanpeng
     * @date : 2020-05-17 22:55
     * @version: v1.0.0
     */
    private void group3() {
        BlockingQueue queue = new ArrayBlockingQueue(3);
        // provider
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    queue.put("product" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "provider").start();
        // consumer
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    System.out.format("%s %s %s\n", "group3", Thread.currentThread().getName(), queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer").start();
    }

    /**
     * 方法类型：超时
     * 插入 offer(e, time, unit)
     * 移除 poll(time, unit)
     * 检查 不可用
     *
     * @description:
     * @author: tanpeng
     * @date : 2020-05-17 22:55
     * @version: v1.0.0
     */
    private void group4() {
        BlockingQueue queue = new ArrayBlockingQueue(3);
        // provider
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    queue.offer("product" + i, 5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "provider").start();
        // consumer
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    System.out.format("%s %s %s\n", "group4", Thread.currentThread().getName(), queue.poll(5, TimeUnit.SECONDS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer").start();
    }

}
