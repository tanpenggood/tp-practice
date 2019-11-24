package com.itplh.demo2.supplier;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * @description: 生产型接口 Supplier 指定什么数据类型，就返回什么数据类型
 *      T get();
 * @author: tanpeng
 * @date: 2019-11-24 11:44
 * @version: v1.0.0
 */
public class TestSupplier {

    private static String getString(Supplier<String> s) {
        return s.get();
    }

    private static Object getObject(Supplier<?> s) {
        return s.get();
    }

    public static void main(String[] args) {
        System.out.println(getString(() -> "Supplier getString"));

        System.out.println(getObject(() -> "Supplier getObject"));
        System.out.println(getObject(() -> 123456));
        System.out.println(getObject(() -> 123456.456));
        System.out.println(getObject(() -> (char)65));
        System.out.println(getObject(() -> new HashMap<String, String>(){{put("key", "value");}}));
    }
}
