package com.itplh.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Mono 可以发送 0 到 1 个数据。
 *
 * @author: tanpenggood
 * @date: 2020-10-22 23:38
 */
public class TestMono {

    public static void main(String[] args) {
        // 1. 构建发布者
        Mono<String> mono1 = Mono.just("a");
        Mono<String> mono2 = Mono.from(Flux.just("b"));

        // 2. 订阅发布者
        // 若不主动订阅发布者，则不会有任何数据被发送。
        // 订阅者既可以是Consumer函数接口，也可以是Subscriber接口的实现。
        mono1.subscribe(System.out::println);
        mono2.subscribe(new Subscriber<String>() {
            volatile Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                System.out.println("初始化请求一个数据");
                subscription.request(1);
            }

            @Override
            public void onNext(String s) {
                System.out.println("当前数据：" + s);
                System.out.println("再请求一个数据");
                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("处理完成");
            }
        });

        // 3. 处理操作
        // 像Stream一样对其中的数据进行处理操作
        Mono.just("hello")
                .map(s -> s += " world")
                .filter(s -> s.length() > 5)
                .map(String::toUpperCase)
                .subscribe(System.out::println);
    }

}
