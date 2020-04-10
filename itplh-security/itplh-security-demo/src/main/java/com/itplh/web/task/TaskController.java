package com.itplh.web.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-09 16:45
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {

    private int initialCapacity = 10;
    private float LOAD_FACTOR = 0.75f;
    private Map<Long, Thread> taskThreadMap = new ConcurrentHashMap<>(initialCapacity);

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @GetMapping("/start")
    public DeferredResult start() {
        DeferredResult result = new DeferredResult();
        CompletableFuture.supplyAsync(() -> {
            if (taskThreadMap.size() > (initialCapacity * LOAD_FACTOR)) {
                return "busy...";
            }

            Thread thread = new Thread(() -> {
                while (true) {
                    log.info("{} 正在工作...", Thread.currentThread().getId());
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        log.error("TimeUnit.SECONDS.sleep(2) {}", e.getMessage());
                    }
                }
            });
            thread.setName("task thread");
            thread.start();
            taskThreadMap.put(thread.getId(), thread);
            return thread.getId() + " is working...";
        }, threadPoolTaskExecutor)
                .thenAccept(res -> result.setResult(res));
        return result;
    }

    @GetMapping("/stop/{threadId}")
    public DeferredResult stop(@PathVariable Long threadId) {
        DeferredResult result = new DeferredResult();
        CompletableFuture.supplyAsync(() -> {
            Thread thread = taskThreadMap.get(threadId);
            Optional.ofNullable(thread).ifPresent(t -> {
                t.stop();
                taskThreadMap.remove(threadId);
            });
            return "stop " + threadId + " success";
        }, threadPoolTaskExecutor)
                .thenAccept(res -> result.setResult(res));
        return result;
    }

    @GetMapping("/threads")
    public DeferredResult threadMap() {
        DeferredResult result = new DeferredResult();
        CompletableFuture.supplyAsync(() -> taskThreadMap.keySet(), threadPoolTaskExecutor)
                .thenAccept(res -> result.setResult(res));
        return result;
    }
}
