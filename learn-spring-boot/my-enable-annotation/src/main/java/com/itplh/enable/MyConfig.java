package com.itplh.enable;

import org.springframework.context.annotation.Bean;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-11 10:54
 * @version: 1.0.0
 */
public class MyConfig {
    @Bean
    public Dog dog() {
        return new Dog();
    }

    @Bean
    public Cat cat() {
        return new Cat();
    }
}
