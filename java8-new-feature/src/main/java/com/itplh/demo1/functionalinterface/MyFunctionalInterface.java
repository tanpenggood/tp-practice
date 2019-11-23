package com.itplh.demo1.functionalinterface;

/**
 * @description: 定义函数式接口：有且只有一个抽象方法的接口，函数式接口可以有default的方法
 * 使用FunctionalInterface注解，可以在编译时检查这个接口是否为函数式接口
 * 就像Override注解一样，在编译时就校验了这个方法是否为重写父类的方法
 * @author: tanpeng
 * @date : 2019-11-23 21:02
 * @version: v1.0.0
 */
@FunctionalInterface
public interface MyFunctionalInterface {

    String getName();

}
