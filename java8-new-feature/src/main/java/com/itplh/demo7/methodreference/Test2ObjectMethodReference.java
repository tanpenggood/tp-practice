package com.itplh.demo7.methodreference;

/**
 * @description:
 * 通过对象引用方法
 * 1、该对象已经存在
 * 2、改方法存在
 * @author: tanpeng
 * @date: 2019-12-17 23:09
 * @version: v1.0.0
 */
public class Test2ObjectMethodReference {

    public static void main(String[] args) {

        // 1、普通调用
        Test1MethodReference.printString(new Printable() {
            @Override
            public void print(String s) {
                new StringUtils().printUpperCaseString(s);
            }
        });

        // 2、lambda调用 因为Printable是一个函数式接口，所以可以使用lambda
        Test1MethodReference.printString(s -> new StringUtils().printUpperCaseString(s));

        // 3、MethodReference调用
        StringUtils stringUtils = new StringUtils();
        Test1MethodReference.printString(stringUtils::printUpperCaseString);

    }

}


