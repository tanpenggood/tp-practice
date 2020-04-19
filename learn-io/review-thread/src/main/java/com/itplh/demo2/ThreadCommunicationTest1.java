package com.itplh.demo2;

import com.itplh.demo1.ThreadSafe;
import sun.jvm.hotspot.utilities.WorkerThread;

import java.util.concurrent.TimeUnit;

/**
 * @description: 线程通信(等待唤醒机制)
 * wait notify 控制线程通信
 * example：实现两个线程交替工作, 将count变量从0加到200
 * @author: tanpeng
 * @date: 2020-04-19 22:14
 * @version: v1.0.0
 */
@ThreadSafe
public class ThreadCommunicationTest1 {

    public static final String lock = "LOCK";
    public static volatile int count = 0;

    public static void main(String[] args) {
        WorkerThread workerThread1 = new WorkerThread();
        WorkerThread workerThread2 = new WorkerThread();

        workerThread1.setName("worker thread 1");
        workerThread2.setName("worker thread 2");

        workerThread1.start();
        workerThread2.start();
    }

    @ThreadSafe
    static class WorkerThread extends Thread {

        private int loop = 100;

        @ThreadSafe
        @Override
        public void run() {
            for (int i = 0; i < loop; i++) {
                synchronized (ThreadCommunicationTest1.lock) {
                    System.out.println(String.format("%s %s %s", ++ThreadCommunicationTest1.count, Thread.currentThread().getName(), "is working."));
                    // 模拟计算耗时
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 唤醒其他线程
                    ThreadCommunicationTest1.lock.notify();
                    // 进入 WAITING 状态
                    if (ThreadCommunicationTest1.count < loop * 2) {
                        try {
                            ThreadCommunicationTest1.lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}

