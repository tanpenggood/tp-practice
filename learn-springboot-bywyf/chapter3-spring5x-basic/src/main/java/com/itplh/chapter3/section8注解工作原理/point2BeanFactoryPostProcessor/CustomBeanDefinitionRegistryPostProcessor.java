package com.itplh.chapter3.section8注解工作原理.point2BeanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

/**
 * @author: tanpenggood
 * @since: 2020-06-06 16:32
 */
@Component
public class CustomBeanDefinitionRegistryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // a.new一个类路径Bean定义扫描器，它的入参是BeanDefinitionRegistry类型，而
        // ConfigurableListableBeanFactory是它的子类，可强制转换使用。当然，我们可以让类直接实现
        // BeanDefinitionRegistryPostProcessor接口，它的postProcessBeanDefinitionRegistry方法参数中直
        // 接提供了BeanDefinitionRegistry的对象
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner((BeanDefinitionRegistry) beanFactory);
        // b.为扫描器添加加包含注解@CustomBean的过滤器
        scanner.addIncludeFilter(new AnnotationTypeFilter(CustomBean.class));
        // c.在包com.itplh.section8注解工作原理.point2BeanFactoryPostProcessor中扫描注解
        scanner.scan("com.itplh.chapter3.section8注解工作原理.point2BeanFactoryPostProcessor");
    }
}
