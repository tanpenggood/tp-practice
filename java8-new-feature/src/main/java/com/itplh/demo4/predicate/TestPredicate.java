package com.itplh.demo4.predicate;

import java.util.function.Predicate;

/**
 * @description: 断言型接口 Predicate
 *      boolean test(T t);
 *      default Predicate<T> and(Predicate<? super T> other) {
 *         Objects.requireNonNull(other);
 *         return (t) -> test(t) && other.test(t);
 *     }
 *     default Predicate<T> or(Predicate<? super T> other) {
 *         Objects.requireNonNull(other);
 *         return (t) -> test(t) || other.test(t);
 *     }
 *     default Predicate<T> negate() {
 *         return (t) -> !test(t);
 *     }
 *     static <T> Predicate<T> isEqual(Object targetRef) {
 *         return (null == targetRef)
 *                 ? Objects::isNull
 *                 : object -> targetRef.equals(object);
 *     }
 * @author: tanpeng
 * @date: 2019-11-24 12:20
 * @version: v1.0.0
 */
public class TestPredicate {

    private static boolean testString(String str, Predicate<String> predicate) {
        return predicate.test(str);
    }

    /**
     * @description: 逻辑与，等价于&&
     * @author: tanpeng
     * @date : 2019-11-24 12:45
     * @version: v1.0.0
     * @param str 1
     * @param predicate1 2
     * @param predicate2 3
     * @return : boolean
     */
    private static boolean andPredicate(String str, Predicate<String> predicate1, Predicate<String> predicate2) {
        return predicate1.and(predicate2).test(str);
    }

    /**
     * @description: 逻辑或，等价于||
     * @author: tanpeng
     * @date : 2019-11-24 12:46
     * @version: v1.0.0
     * @param str 1
     * @param predicate1 2
     * @param predicate2 3
     * @return : boolean
     */
    private static boolean orPredicate(String str, Predicate<String> predicate1, Predicate<String> predicate2) {
        return predicate1.or(predicate2).test(str);
    }

    /**
     * @description: 逻辑非，等价于!
     * @author: tanpeng
     * @date : 2019-11-24 12:46
     * @version: v1.0.0
     * @param str 1
     * @param predicate 2
     * @return : boolean
     */
    private static boolean negatePredicate(String str, Predicate<String> predicate) {
        return predicate.negate().test(str);
    }

    public static void main(String[] args) {
        System.out.println(testString("Test Predicate", str -> str.length() > 5));
        System.out.println(testString("Test Predicate", str -> str.length() < 5));
        System.out.println(testString("Test Predicate", Predicate.isEqual("Test Predicate")));

        System.out.println(andPredicate("Test Predicate",
                str -> str.length() > 5,
                str -> str.contains("T")));

        System.out.println(orPredicate("Test Predicate",
                str -> str.length() > 5,
                str -> str.contains("TAN")));

        System.out.println(negatePredicate("Test Predicate", str -> str.length() > 5));
    }
}
