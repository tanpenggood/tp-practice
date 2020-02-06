package com.itplh.demo8.completablefuture;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步计算
 *  - 所谓异步调用其实就是实现一个可无需等待被调用函数的返回值而让操作继续运行的方法。在 Java 语言中，简单的讲就是另启一个线程来完成调用中的部分计算，使调用继续运行或返回，而不需要等待计算结果。但调用者仍需要取线程的计算结果。
 *  - JDK5新增了Future接口，用于描述一个异步计算的结果。虽然 Future 以及相关使用方法提供了异步执行任务的能力，但是对于结果的获取却是很不方便，只能通过阻塞或者轮询的方式得到任务的结果。阻塞的方式显然和我们的异步编程的初衷相违背，轮询的方式又会耗费无谓的 CPU 资源，而且也不能及时地得到计算结果。
 *  - 以前我们获取一个异步任务的结果可能是这样写的：https://images2018.cnblogs.com/blog/874963/201807/874963-20180705101315432-1126516064.png
 * <p>
 * Future 接口的局限性
 *  Future接口可以构建异步应用，但依然有其局限性。它很难直接表述多个Future 结果之间的依赖性。实际开发中，我们经常需要达成以下目的：
 *  - 将多个异步计算的结果合并成一个
 *  - 等待Future集合中的所有任务都完成
 *  - Future完成事件（即，任务完成以后触发执行动作）
 * <p>
 * 函数式编程
 *  面向对象编程 ---> 抽象数据
 *  函数式编程   ---> 抽象行为
 * <p>
 * CompletionStage
 *  - CompletionStage代表异步计算过程中的某一个阶段，一个阶段完成以后可能会触发另外一个阶段
 *  - 一个阶段的计算执行可以是一个Function，Consumer或者Runnable。比如：stage.thenApply(x -> square(x)).thenAccept(x -> System.out.print(x)).thenRun(() -> System.out.println())
 *  - 一个阶段的执行可能是被单个阶段的完成触发，也可能是由多个阶段一起触发
 * <p>
 * CompletableFuture
 *  - 在Java8中，CompletableFuture提供了非常强大的Future的扩展功能，可以帮助我们简化异步编程的复杂性，并且提供了函数式编程的能力，可以通过回调的方式处理计算结果，也提供了转换和组合 CompletableFuture 的方法。
 *  - 它可能代表一个明确完成的Future，也有可能代表一个完成阶段（ CompletionStage ），它支持在计算完成以后触发一些函数或执行某些动作。
 *  - 它实现了Future和CompletionStage接口 public class CompletableFuture<T> implements Future<T>, CompletionStage<T>
 * <p>
 * CompletableFuture基本用法
 * - 创建CompletableFuture
 *      - public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
 *      - public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
 *      - public static CompletableFuture<Void> runAsync(Runnable runnable)
 *      - public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
 * - thenApply 有返回值，生产，相当于map
 * 当前阶段正常完成以后执行，而且当前阶段的执行的结果会作为下一阶段的输入参数。thenApplyAsync默认是异步执行的。这里所谓的异步指的是不在当前线程内执行。
 *      - public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
 *      - public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
 *      - public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
 *      - thenAccept与thenRun 无返回值，消费型
 *      - public CompletableFuture<Void> thenAccept(Consumer<? super T> action)
 *      - public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action)
 *      - public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor)
 *      - public CompletableFuture<Void> thenRun(Runnable action)
 *      - public CompletableFuture<Void> thenRunAsync(Runnable action)
 *      - public CompletableFuture<Void> thenRunAsync(Runnable action, Executor executor)
 *  可以看到，thenAccept和thenRun都是无返回值的。如果说thenApply是不停的输入输出的进行生产，那么thenAccept和thenRun就是在进行消耗。它们是整个计算的最后两个阶段。
 *  同样是执行指定的动作，同样是消耗，二者也有区别：
 *      thenAccept接收上一阶段的输出作为本阶段的输入
 *      thenRun根本不关心前一阶段的输出，根本不关心前一阶段的计算结果，因为它不需要输入参数
 *  - thenCombine 整合两个计算结果
 *      - public <U,V> CompletableFuture<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)
 *      - public <U,V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)
 *      - public <U,V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn, Executor executor)
 * - whenComplete
 *      - public CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action)
 *      - public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action)
 *      - public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor)
 * <b>
 * 事实上，如果每个操作都很简单的话（比如：上面的例子中按照id去查）没有必要用这种多线程异步的方式，因为创建线程还需要时间，还不如直接同步执行来得快。
 * 事实证明，只有当每个操作很复杂需要花费相对很长的时间（比如，调用多个其它的系统的接口；
 * 比如，商品详情页面这种需要从多个系统中查数据显示的）的时候用CompletableFuture才合适，不然区别真的不大，还不如顺序同步执行。
 * </b>
 *
 * @description: CompletableFuture 常用API及使用
 * @author: tanpeng
 * @date: 2020-01-19 22:43
 * @version: v1.0.0
 */
public class TestCompletableFuture {

    private static final ExecutorService FIXED_THREAD_POOL = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {

        // 同步获取CompletableFuture
        CompletableFuture.completedFuture(mapStrAppend("hello syncFuture", ""));
        // 异步获取CompletableFuture
        CompletableFuture.supplyAsync(() -> mapStrAppend("hello asyncFuture1 fork join", ""));
        CompletableFuture.supplyAsync(() -> mapStrAppend("hello asyncFuture2 executor", ""), FIXED_THREAD_POOL);

        // runAsync ForkJoinPool
        CompletableFuture.runAsync(() -> printValueAndThreadName("hello runAsync fork join"));
        // runAsync Executor
        CompletableFuture.runAsync(() -> printValueAndThreadName("hello runAsync executor"), FIXED_THREAD_POOL);

        // thenApply
        CompletableFuture<String> thenApplyFuture = CompletableFuture.supplyAsync(() -> "hello ");
        thenApplyFuture.thenApply(str -> mapStrAppend(str, "thenApply"));
        thenApplyFuture.thenApplyAsync(str -> mapStrAppend(str, "thenApplyAsync fork join"));
        thenApplyFuture.thenApplyAsync(str -> mapStrAppend(str, "thenApplyAsync executor"), FIXED_THREAD_POOL);

        // thenAccept
        CompletableFuture<String> thenAcceptFuture = CompletableFuture.supplyAsync(() -> "hello thenAccept");
        thenAcceptFuture.thenAccept(str -> printValueAndThreadName(str));
        thenAcceptFuture.thenAcceptAsync(str -> printValueAndThreadName(str));
        thenAcceptFuture.thenAcceptAsync(str -> printValueAndThreadName(str), FIXED_THREAD_POOL);
        // thenRun
        CompletableFuture<String> thenRunFuture = CompletableFuture.supplyAsync(() -> "hello");
        thenRunFuture.thenRun(() -> printValueAndThreadName("hello thenRun"));
        thenRunFuture.thenRunAsync(() -> printValueAndThreadName("hello thenRunAsync fork join"));
        thenRunFuture.thenRunAsync(() -> printValueAndThreadName("hello thenRunAsync executor"), FIXED_THREAD_POOL);

        // thenCombine
        CompletableFuture.supplyAsync(() -> "hello ")
                .thenApply(str -> str += "world ")
                .thenApply(String::toUpperCase)
                .thenCombine(CompletableFuture.completedFuture("thenCombine"), (r1, r2) -> {
                    printValueAndThreadName(r1 + r2);
                    return r1 + r2;
                });
        CompletableFuture.supplyAsync(() -> "hello ")
                .thenApply(str -> str += "world ")
                .thenApply(String::toUpperCase)
                .thenCombineAsync(CompletableFuture.completedFuture("thenCombineAsync fork join"), (r1, r2) -> {
                    printValueAndThreadName(r1 + r2);
                    return r1 + r2;
                });
        CompletableFuture.supplyAsync(() -> "hello ")
                .thenApply(str -> str += "world ")
                .thenApply(String::toUpperCase)
                .thenCombineAsync(CompletableFuture.completedFuture("thenCombineAsync executor"), (r1, r2) -> {
                    printValueAndThreadName(r1 + r2);
                    return r1 + r2;
                }, FIXED_THREAD_POOL)
                .whenComplete((r, e) -> System.out.println(r));

        // whenComplete
        CompletableFuture[] futures = Arrays.asList("hello", "whenComplete", "c").stream()
                .map(str -> CompletableFuture.supplyAsync(() -> str).thenApply(String::toUpperCase))
                .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(futures)
                .whenComplete((r, err) -> {
                    printValueAndThreadName("whenComplete");
                    Optional.ofNullable(err).ifPresent(System.out::println);
                });
        CompletableFuture.allOf(futures)
                .whenCompleteAsync((r, err) -> {
                    printValueAndThreadName("whenCompleteAsync fork join");
                    Optional.ofNullable(err).ifPresent(System.out::println);
                });
        CompletableFuture.allOf(futures)
                .whenCompleteAsync((result, err) -> {
                    printValueAndThreadName("whenCompleteAsync executor");
                    Optional.ofNullable(err).ifPresent(System.out::println);
                }, FIXED_THREAD_POOL);

        FIXED_THREAD_POOL.shutdown();
    }

    private static String mapStrAppend(String str, String appendStr) {
        str += appendStr;
        printValueAndThreadName(str);
        return str;
    }

    private static void printValueAndThreadName(String str) {
        System.out.println(str + "\t" + Thread.currentThread().getName());
    }

}
