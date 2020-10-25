package com.itplh.integration;

import com.itplh.integration.gateway.SendingGateway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author: tanpenggood
 * @date: 2020-10-25 23:41
 */
@SpringBootApplication
public class LearningSpringIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningSpringIntegrationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(SendingGateway gateway) {
        return args -> {
            // 将会被路由到fileChannel
            gateway.send("greeting.txt", "hello world");
            // 将会被路由到fileChannel
            gateway.send("greeting.txt", "hello tanpeng");
            // 将会被忽略
            gateway.send("greeting.txt", "good morning");
            // 将会被路由到emailChannel
            gateway.send("greeting.txt", "hi world");
            // 将会被路由到emailChannel
            gateway.send("greeting.txt", "hi tanpeng");
        };
    }
}
