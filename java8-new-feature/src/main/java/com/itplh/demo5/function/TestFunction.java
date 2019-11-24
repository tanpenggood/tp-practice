package com.itplh.demo5.function;

import java.util.function.Function;

/**
 * @description: 生产型接口 Function 指定数据类型T，返回数据类型R（当T、R数据类型相同时，可替代Supplier）
 *      R apply(T t);
 *      default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
 *         Objects.requireNonNull(after);
 *         return (T t) -> after.apply(apply(t));
 *     }
 *     default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
 *         Objects.requireNonNull(before);
 *         return (V v) -> apply(before.apply(v));
 *     }
 *     static <T> Function<T, T> identity() {
 *         return t -> t;
 *     }
 * @author: tanpeng
 * @date: 2019-11-24 13:47
 * @version: v1.0.0
 */
public class TestFunction {

    /**
     * @description: String转Integer
     * @author: tanpeng
     * @date : 2019-11-24 13:57
     * @version: v1.0.0
     * @param str 1
     * @param function 2
     * @return : java.lang.Integer
     */
    private static Integer stringConvertInteger(String str, Function<String, Integer> function) {
        return function.apply(str);
    }

    /**
     * @description: T、R数据类型都为String，等价于Supplier
     * @author: tanpeng
     * @date : 2019-11-24 13:59
     * @version: v1.0.0
     * @param str 1
     * @param function 2
     * @return : java.lang.String
     */
    private static String convertIdentity(String str, Function<String, String> function) {
        return function.apply(str);
    }

    /**
     * @description: 按顺序多次转换（先执行调用者，再执行参数）
     * String -> Integer
     * Integer -> String
     *
     * @author: tanpeng
     * @date : 2019-11-24 14:07
     * @version: v1.0.0
     * @param str 1
     * @param function1 2
     * @param function2 3
     * @return : java.lang.String
     */
    private static String andThenConvert(String str, Function<String, Integer> function1, Function<Integer, String> function2) {
        return function1.andThen(function2).apply(str);
    }

    /**
     * @description: 多次转换类型（先执行参数，再执行调用者，与andThen相反）
     *  String -> Integer
     *  Integer -> String
     *
     * @author: tanpeng
     * @date : 2019-11-24 14:27
     * @version: v1.0.0
     * @param str 1
     * @param function1 2
     * @param function2 3
     * @return : java.lang.String
     */
    private static String composeConvert(String str, Function<String, Integer> function1, Function<Integer, String> function2) {
        return function2.compose(function1).apply(str);
    }

    public static void main(String[] args) {
        System.out.println(stringConvertInteger("10000", str -> Integer.parseInt(str)));
//        System.out.println(stringConvertInteger("10000a", str -> Integer.parseInt(str)));

        System.out.println(convertIdentity("10000a", Function.identity())); // identity返回当前正在执行的方法

        System.out.println(andThenConvert("10000",
                str -> Integer.parseInt(str) + 10,
                i -> i.toString()));

        System.out.println(composeConvert("10000",
                str -> Integer.parseInt(str) + 10,
                i -> i.toString()));
    }
}
