package com.itplh.section3SpringBoot的配置.point1应用配置;

import com.itplh.Application;
import org.springframework.boot.SpringApplication;

/**
 * Spring Boot除了做了大量的自动配置外，还提供了一些其他默认配置
 * 如，配置的应用监听器 在（spring-boot-2.2.5.RELEASE.jar、spring-boot-autoconfigure-2.2.5.RELEASE.jar）
 * 类路径下文件META-INF/spring.factories中的工厂名为org.springframework.context.ApplicationListener
 * 这给我们一个提示，即可以通过相同的方法来注册监听器。在当前应用中新建resources/META-INF/spring.factories文件
 * 添加配置：
 * org.springframework.context.ApplicationListener=com.itplh.listener.MyListener
 *
 * @author: tanpenggood
 * @since: 2020-06-06 23:49
 */
public class Usage4其他默认配置 {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
