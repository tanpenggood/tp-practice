package com.itplh;

import com.itplh.starter.HelloProperties;
import com.itplh.starter.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-10 15:26
 * @version: 1.0.0
 */
@SpringBootApplication
public class MyStarterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MyStarterApplication.class, args);
        System.out.println(context.getBean(HelloProperties.class));
        System.out.println(context.getBean(HelloService.class));
    }
}
