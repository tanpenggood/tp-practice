package com.itplh.chapter3.section8注解工作原理.point2BeanFactoryPostProcessor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: tanpenggood
 * @since: 2020-06-06 15:12
 */
@Configuration("com.itplh.chapter3.section8注解工作原理.point2BeanFactoryPostProcessor.JavaConfig")
public class JavaConfig {

    // 因为CustomBeanService是自定义的Bean，所以IDEA的检测会显示红色，但可以正常执行
    @Bean("com.itplh.chapter3.section8注解工作原理.point2BeanFactoryPostProcessor.JavaConfig.commandLineRunner")
    public CommandLineRunner commandLineRunner(CustomBeanService customBeanService) {
        return args -> customBeanService.doSomething();
    }

}
