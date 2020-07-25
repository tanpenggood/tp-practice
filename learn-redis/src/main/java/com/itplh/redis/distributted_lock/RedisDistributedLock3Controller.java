package com.itplh.redis.distributted_lock;

import com.itplh.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import static com.itplh.redis.distributted_lock.Resource.count;

/**
 * @author: tanpenggood
 * @date: 2020-07-25 18:23
 * @since: java 8
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/redis")
public class RedisDistributedLock3Controller {
    private RedissonClient redissonClient;

    /**
     * Feature:
     * 保证了原子性
     * 保证了锁始终会被释放，不会产生deadlock
     * hash的key存储clientId，保证了加锁对象与释放锁对象的身份一致性
     * 使用的数据结构是hash，它是一个独占锁，获取锁有自旋机制，获取锁失败会进行自旋等待
     * <p>
     * Optimize:
     * 自行由于自行指定了leaseTime，所以不会再为锁续期，会出现业务还没执行完，而锁已经过期，其他线程可以获取到锁（此时与/redis/lock接口一致）
     * 使用异步接口，提高服务端吞吐量
     * Callable
     * DeferredResult
     */
    @GetMapping(value = "/lock3", produces = "application/json")
    public Result redisLock() {
        String lockKey = "lock3_key";
        // 获取RLock对象
        RLock lock = redissonClient.getLock(lockKey);
        // 获取redis分布式锁
        lock.lock(30, TimeUnit.SECONDS);
        try {
            // 获取到锁 do business
            if (count > 0) {
                log.info("恭喜你，抢到了商品！商品余量为: {}", --count);
                return Result.ok("恭喜你，抢到了商品！");
            } else {
                log.info("很遗憾，商品被抢光了！商品余量为: {}", count);
                return Result.error("很遗憾，商品被抢光了！");
            }
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}