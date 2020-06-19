package com.itplh.web.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author: tanpeng
 * @since: 2020-06-19 10:49
 */
@Slf4j
@RestController
@RequestMapping("/async/deferred")
public class AsyncDeferredResultController {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private Map<String, DeferredResult<String>> map = new HashMap<>();

    @GetMapping("/{id}")
    public DeferredResult<String> deferred(@PathVariable String id) {
        DeferredResult<String> result = new DeferredResult<>();
        map.put(id, result);
        return result;
    }

    @GetMapping("/{id}/invoke")
    public void invokeDeferred(@PathVariable String id) {
        log.info("主线程开始...");
        DeferredResult<String> result = map.get(id);
        Optional.ofNullable(result).ifPresent(res ->
                CompletableFuture.runAsync(() -> {
                    log.info("副线程开始...");
                    result.setResult("hello deferred result");
                    map.remove(id);
                    log.info("副线程结束...");
                }, threadPoolTaskExecutor));
        log.info("主线程结束...");
    }

}
