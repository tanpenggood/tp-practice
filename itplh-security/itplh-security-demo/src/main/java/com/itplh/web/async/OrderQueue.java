package com.itplh.web.async;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-20 12:19
 * @version: v1.0.0
 */
@Component
@Slf4j
@Data
public class OrderQueue {

    private BlockingQueue<String> placeOrderQueue = new ArrayBlockingQueue<>(1);

    public void submitPlaceOrder(String placeOrder) {
        new Thread(() -> {
            log.info("接到下订单请求，" + placeOrder);
            try {
                placeOrderQueue.put(placeOrder);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("订单已放入处理队列，" + placeOrder);
        }, "order provider thread").start();
    }

}
