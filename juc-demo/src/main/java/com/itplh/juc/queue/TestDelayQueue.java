package com.itplh.juc.queue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列
 * 什么是延时队列？
 * 顾名思义：首先它要具有队列的特性，再给它附加一个延迟消费队列消息的功能，也就是说可以指定队列中的消息在哪个时间点被消费。
 * 延时队列在项目中的应用还是比较多的，尤其像电商类平台：
 * 1、订单成功后，在30分钟内没有支付，自动取消订单
 * 2、外卖平台发送订餐通知，下单成功后60s给用户推送短信。
 * 3、如果订单一直处于某一个未完结状态时，及时处理关单，并退还库存
 * 4、淘宝新建商户一个月内还没上传商品信息，将冻结商铺
 * 5、定时任务等
 * 上边的这些场景都可以应用延时队列解决
 * <p>
 * 参考：一口气说出6种延时队列的实现方案 https://zhuanlan.zhihu.com/p/140796334
 *
 * @description:
 * @author: tanpeng
 * @date: 2020-05-15 17:31
 * @version: 1.0.0
 */
public class TestDelayQueue {

    DelayQueue<DelayTask> queue = new DelayQueue<>();

    public static void main(String[] args) {
        TestDelayQueue test = new TestDelayQueue();
        test.providerByPut();
        System.out.println("begin time:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        test.consumerByTake();
//        test.consumerByPoll();
    }

    /**
     * DelayQueue的put方法是线程安全的，因为put方法内部使用了ReentrantLock锁进行线程同步
     *
     * @description:
     * @author: tanpeng
     * @date : 2020-05-17 16:53
     * @version: v1.0.0
     */
    private void providerByPut() {
        queue.put(new DelayTask("task1", 5, TimeUnit.SECONDS));
        queue.put(new DelayTask("task2", 8, TimeUnit.SECONDS));
        queue.put(new DelayTask("task3", 2, TimeUnit.SECONDS));
    }

    /**
     * poll() 为非阻塞获取，没有到期的元素直接返回null
     * 该方法可用于对时间要求不精确的定时任务的场景
     *
     * @description:
     * @author: tanpeng
     * @date : 2020-05-17 16:55
     * @version: v1.0.0
     */
    private void consumerByPoll() {
        new Thread(() -> {
            while (true) {
                runIfPresent(queue.poll());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer-poll").start();
    }

    /**
     * take() 阻塞方式获取，没有到期的元素线程将会等待
     *
     * @description:
     * @author: tanpeng
     * @date : 2020-05-17 16:56
     * @version: v1.0.0
     */
    private void consumerByTake() {
        new Thread(() -> {
            while (true) {
                try {
                    runIfPresent(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer-take").start();
    }

    private void runIfPresent(DelayTask task) {
        Optional.ofNullable(task).ifPresent(t -> {
            System.out.format("[%s], name:{%s}, time:{%s}\n",
                    Thread.currentThread().getName(),
                    t.getTaskName(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        });
    }

}
