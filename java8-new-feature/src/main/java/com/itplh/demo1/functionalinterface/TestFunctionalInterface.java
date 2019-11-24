package com.itplh.demo1.functionalinterface;

/**
 * @description:
 * @author: tanpeng
 * @date : 2019-11-24 12:43
 * @version: v1.0.0
 */
public class TestFunctionalInterface {

    /**
     * @description: 定义一个参数为函数式接口的方法
     * @author: tanpeng
     * @date : 2019-11-23 21:02
     * @version: v1.0.0
     * @param fi 1
     * @return : java.lang.String
     */
    private static String getName(MyFunctionalInterface fi) {
        return fi.getName();
    }

    public static void main(String[] args) {
        // 1.实现类
        System.out.println(getName(new MyFunctionalInterfaceImpl()));

        // 2.supplier
        System.out.println(getName(() -> "supplier"));
    }
}
