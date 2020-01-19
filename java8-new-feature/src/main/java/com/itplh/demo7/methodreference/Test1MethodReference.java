package com.itplh.demo7.methodreference;

/**
 * @description:\
 * 方法引用简介
 * @author: tanpeng
 * @date: 2019-12-17 22:47
 * @version: v1.0.0
 */
public class Test1MethodReference {

    /**
     * @description:
     * 定义一个打印字符串的方法，参数为Printable接口
     * @author: tanpeng
     * @date : 2019-12-17 22:51
     * @version: v1.0.0
     */
    public static void printString(Printable printable) {
        printable.print("Hello MethodReference");
    }

    public static void main(String[] args) {

        // 1、普通调用
        printString(new Printable() {
            @Override
            public void print(String s) {
                System.out.println(s);
            }
        });

        // 2、lambda调用 因为Printable是一个函数式接口，所以可以使用lambda
        printString(s -> System.out.println(s));

        // 3、MethodReference调用
        printString(System.out::println);

    }
}
