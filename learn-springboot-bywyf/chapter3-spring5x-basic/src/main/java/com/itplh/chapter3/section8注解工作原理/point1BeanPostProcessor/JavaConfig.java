package com.itplh.chapter3.section8注解工作原理.point1BeanPostProcessor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: tanpenggood
 * @since: 2020-06-06 15:12
 */
@Configuration("com.itplh.chapter3.section8注解工作原理.point1BeanPostProcessor.JavaConfig")
public class JavaConfig {

    @Bean("com.itplh.chapter3.section8注解工作原理.point1BeanPostProcessor.JavaConfig.commandLineRunner")
    public CommandLineRunner commandLineRunner(DemoLoggerService demoLoggerService) {
        return args -> demoLoggerService.doSomething();
    }

}
