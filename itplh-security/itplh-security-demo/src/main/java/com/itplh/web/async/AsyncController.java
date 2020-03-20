package com.itplh.web.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-20 11:56
 * @version: v1.0.0
 */
@RestController
@Slf4j
public class AsyncController {

    /**
     * 同步接口
     */
    @GetMapping("/order")
    public Map order() throws InterruptedException {
        log.info("sync");
        log.info("主线程开始");
        TimeUnit.SECONDS.sleep(1);
        log.info("主线程结束");
        return new OrderApiInfo().getOrderApiMap();
    }

    /**
     * 同步接口
     * 这个接口任然是同步接口，因为调用call()是阻塞的
     */
    @GetMapping("/order/callable")
    public Map orderCallable() throws Exception {
        log.info("sync callable");
        log.info("主线程开始");
        Callable<Map> callable = () -> {
            log.info("副线程开始");
            TimeUnit.SECONDS.sleep(1);
            log.info("副线程结束");
            return new OrderApiInfo().getOrderApiMap();
        };
        Map result = callable.call();

        log.info("主线程结束");
        return result;
    }

    @Autowired
    private OrderQueue orderQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    /**
     * 异步接口
     */
    @GetMapping("/order/deferred_result")
    public DeferredResult<Map> orderDeferredResult() throws Exception {
        log.info("async deferred result + thread");
        log.info("主线程开始");

        String orderNumber = RandomStringUtils.randomNumeric(8);
        orderQueue.submitPlaceOrder(orderNumber);

        DeferredResult<Map> result = new DeferredResult<>();
        deferredResultHolder.getCompleteOrder().put(orderNumber, result);

        log.info("主线程结束");
        return result;
    }

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 异步接口
     */
    @GetMapping("/order/deferred_result/thread_pool")
    public DeferredResult<Map> orderThreadPool() {
        log.info("async deferred result + thread pool");
        log.info("主线程开始");

        DeferredResult<Map> result = new DeferredResult<>();
        CompletableFuture.supplyAsync(() -> {
            log.info("副线程开始");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("副线程结束");
            return new OrderApiInfo().getOrderApiMap();
        }, threadPoolTaskExecutor)
                .thenAccept(res -> result.setResult(res));

        log.info("主线程结束");
        return result;
    }
}