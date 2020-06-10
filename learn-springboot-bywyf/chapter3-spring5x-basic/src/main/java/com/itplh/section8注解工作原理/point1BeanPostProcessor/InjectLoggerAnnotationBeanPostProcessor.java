package com.itplh.section8注解工作原理.point1BeanPostProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

/**
 * @author: tanpenggood
 * @since: 2020-06-06 14:53
 */
@Component
public class InjectLoggerAnnotationBeanPostProcessor implements BeanPostProcessor {

    // a.指明当前类处理InjectLogger注解
    private Class<? extends Annotation> changeAnnotationType; // a

    public InjectLoggerAnnotationBeanPostProcessor() {
        this.changeAnnotationType = InjectLogger.class; // a
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // InjectLogger作用在字段上
        // b.通过反射机制对类的每个属性(Field)进行处理，第一个参数是Bean的Class，第二个
        // 参数是入参为Field，无返回值的函数接口的Lambda实现
        ReflectionUtils.doWithFields(bean.getClass(), field -> { // b
            // d.新建Logger的实例logger
            if (field.isAnnotationPresent(changeAnnotationType)) { // d
                // c.通过反射机制让当前属性可访问
                ReflectionUtils.makeAccessible(field); // c
                Logger logger = LoggerFactory.getLogger(bean.getClass());
                // e.通过反射将logger值设置到Bean实例的当前属性(field)上
                field.set(bean, logger); // e
            }
        });

        // InjectLogger注解作用于类上
        Stream.of(bean.getClass().getAnnotations())
                .filter(a -> a.annotationType().equals(changeAnnotationType))
                .forEach(a -> {
                    ReflectionUtils.doWithFields(bean.getClass(), field -> {
                        if (field.getType().equals(Logger.class)) {
                            ReflectionUtils.makeAccessible(field);
                            Logger logger = LoggerFactory.getLogger(bean.getClass());
                            field.set(bean, logger);
                        }
                    });
                });

        return bean;
    }
}
