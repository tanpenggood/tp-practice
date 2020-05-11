package com.itplh.enable;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * registerBeanDefinitions方法的参数有一个BeanDefinitionRegistry，
 * BeanDefinitionRegistry可以用来往spring容器中注入bean
 * 如此，我们就可以在registerBeanDefinitions方法里面动态的注入bean
 *
 * @description:
 * @author: tanpeng
 * @date: 2020-05-11 11:10
 * @version: 1.0.0
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        registry.registerBeanDefinition(People.class.getName(),
                BeanDefinitionBuilder.rootBeanDefinition(People.class).getBeanDefinition());

        registry.registerBeanDefinition(User.class.getName(),
                BeanDefinitionBuilder.rootBeanDefinition(User.class).getBeanDefinition());

        registry.registerBeanDefinition(MyConfig.class.getName(),
                BeanDefinitionBuilder.rootBeanDefinition(MyConfig.class).getBeanDefinition());
    }
}
