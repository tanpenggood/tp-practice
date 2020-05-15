package com.itplh.juc.delayqueue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-15 17:31
 * @version: 1.0.0
 */
public class DelayQueueTest {

    DelayQueue<DelayTask> queue = new DelayQueue<>();

    public static void main(String[] args) {
        DelayQueueTest test = new DelayQueueTest();
        test.provider();
        System.out.println("begin time:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        test.consumer();
    }

    private void provider() {
        DelayTask task1 = new DelayTask("task1", 3, TimeUnit.SECONDS);
        DelayTask task2 = new DelayTask("task2",2, TimeUnit.SECONDS);
        DelayTask task3 = new DelayTask("task3",1, TimeUnit.SECONDS);
        queue.put(task1);
        queue.put(task2);
        queue.put(task3);
    }

    /**
     * 参考：一口气说出6种延时队列的实现方案 https://zhuanlan.zhihu.com/p/140796334
     * @description:
     * @author: tanpeng
     * @date : 2020/5/15 18:11
     * @version: v1.0.0
     */
    private void consumer() {
        new Thread(() -> {
            while (true) {
                DelayTask task = queue.poll();
                Optional.ofNullable(task).ifPresent(t -> {
                    System.out.format("name:{%s}, time:{%s}\n",t.getTaskName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                });
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer").start();
    }

}
