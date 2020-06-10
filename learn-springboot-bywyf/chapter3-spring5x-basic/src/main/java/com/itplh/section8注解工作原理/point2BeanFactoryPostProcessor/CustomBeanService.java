package com.itplh.section8注解工作原理.point2BeanFactoryPostProcessor;

import org.springframework.stereotype.Service;

/**
 * @author: tanpenggood
 * @since: 2020-06-06 16:48
 */
@CustomBean
public class CustomBeanService {

    public void doSomething() {
        System.out.println("通过自定义的注解成功注册Bean");
    }

}
