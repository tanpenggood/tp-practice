package com.itplh.web.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-20 12:44
 * @version: v1.0.0
 */
@Component
@Slf4j
public class OrderQueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private OrderQueue orderQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Autowired
    private OrderApiInfo orderApiInfo;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(() -> {
            while (true) {
                try {
                    String orderNumber = orderQueue.getPlaceOrderQueue().take();
                    log.info("开始处理订单，" + orderNumber);
                    TimeUnit.SECONDS.sleep(1);
                    deferredResultHolder.getCompleteOrder().get(orderNumber).setResult(orderApiInfo.getOrderApiMap());
                    log.info("订单处理完毕，" + orderNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "order consumer thread").start();
    }
}
