package com.itplh.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.stream.Stream;

/**
 * Flux 可以发送 0 个到 n 个数据。
 *
 * @author: tanpenggood
 * @date: 2020-10-22 23:11
 */
public class TestFlux {

    public static void main(String[] args) {
        Flux<String> flux1 = Flux.just("a", "b", "b");
        Flux<String> flux2 = Flux.fromArray(new String[]{"c", "d", "e"});
        Flux<String> flux3 = Flux.fromStream(Stream.of("e", "f", "g"));
        Flux<Integer> flux4 = Flux.range(2020, 3);

        flux1.subscribe(System.out::print);
        System.out.println();
        flux2.subscribe(System.out::print);
        System.out.println();
        flux3.subscribe(System.out::print);
        System.out.println();
        flux4.subscribe(new Subscriber<Integer>() {

            volatile Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                System.out.println("初始化请求一个数据");
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("当前数据：" + integer);
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
    }
}
