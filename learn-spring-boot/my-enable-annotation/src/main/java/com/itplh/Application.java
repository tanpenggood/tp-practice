package com.itplh;

import com.itplh.enable.*;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-11 11:19
 * @version: 1.0.0
 */
//@Import(MyImportSelector.class)
@EnableConfigBySelector
//@Import(MyImportBeanDefinitionRegistrar.class)
//@EnableConfigByRegistrar
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        System.out.println(context.getBean(Dog.class));
        System.out.println(context.getBean(Cat.class));
        System.out.println(context.getBean(User.class));
        System.out.println(context.getBean(People.class));
        // 使用 context.getBean("people") 获取不到Bean, why?
        System.out.println(context.getBean("com.itplh.enable.People"));
        // 使用 context.getBean("com.itplh.enable.Cat") 获取不到Bean, why?
        System.out.println(context.getBean("cat"));
    }
}
