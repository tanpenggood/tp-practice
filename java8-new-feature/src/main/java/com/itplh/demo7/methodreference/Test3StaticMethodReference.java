package com.itplh.demo7.methodreference;

/**
 * @description:
 * 通过类名引用静态方法
 * 1、该类存在
 * 2、该静态方法存在
 * @author: tanpeng
 * @date: 2019-12-17 23:09
 * @version: v1.0.0
 */
public class Test3StaticMethodReference {

    /**
     * @description: 将一个整数取绝对值，并输出
     * @author: tanpeng
     * @date : 2020-01-19 21:42
     * @version: v1.0.0
     */
    public static void method(int num, Calcable calcable) {
        System.out.println(calcable.calc(num));
    }

    public static void main(String[] args) {

        // 1、普通调用 求一个数的绝对值
        method(-10, new Calcable() {
            @Override
            public int calc(int num) {
                return Math.abs(num);
            }
        });

        // 2、lambda调用 因为Printable是一个函数式接口，所以可以使用lambda
        method(-10, num -> Math.abs(num));

        // 3、MethodReference调用
        method(-10, Math::abs);

    }

}


