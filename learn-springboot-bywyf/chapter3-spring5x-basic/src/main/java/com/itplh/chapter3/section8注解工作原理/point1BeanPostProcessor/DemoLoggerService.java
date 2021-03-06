package com.itplh.chapter3.section8注解工作原理.point1BeanPostProcessor;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author: tanpenggood
 * @since: 2020-06-06 15:10
 */
@Service
public class DemoLoggerService {

    @InjectLogger
    private Logger logger;

    public void doSomething() {
        logger.info("通过自定义InjectLoggerAnnotationBeanPostProcessor让注解@InjectLogger注入Logger对象");
    }

}
