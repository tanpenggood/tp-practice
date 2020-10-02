package com.itplh.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * 自定义事件监听器
 * 开启异步进行处理事件
 */
@Async
@EnableAsync
@Component
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("*************************************");
        System.out.println(Thread.currentThread().getName());
        // 处理事件
        event.handleEvent();
        System.out.println("*************************************");
    }
}
