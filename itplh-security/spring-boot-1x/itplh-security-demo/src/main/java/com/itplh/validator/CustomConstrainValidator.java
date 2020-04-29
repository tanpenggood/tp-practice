package com.itplh.validator;

import com.itplh.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description: 自定义validator
 * 默认即被Spring容器管理，可以直接调用其他service
 * @author: tanpeng
 * @date: 2020-03-19 18:18
 * @version: v1.0.0
 */
public class CustomConstrainValidator implements ConstraintValidator<CustomConstraint, Object> {

    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(CustomConstraint customConstraint) {
        System.out.println("custom constraint validator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        helloService.greeting("tanpeng");
        System.out.println(value);
        return true;
    }
}
