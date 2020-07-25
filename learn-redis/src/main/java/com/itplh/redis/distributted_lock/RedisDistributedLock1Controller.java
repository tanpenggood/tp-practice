package com.itplh.redis.distributted_lock;

import com.itplh.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.UUID;
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
public class RedisDistributedLock1Controller {
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Feature:
     * 保证了setnx key与设置过期时间的原子性
     * 保证了锁始终会被释放，不会产生deadlock
     * value存储requestId，保证了加锁对象与释放锁对象的身份一致性
     * 使用的数据结构是string，它是一个独占锁，获取锁无自旋机制，要么成功否则就失败
     * <p>
     * Optimize:
     * 业务还没执行完，而lockKey已过期，其他线程可以获取到锁
     * 解决方案一：开启一个后台线程周期性为该锁续期。
     * 解决方案二：使用成熟框架 redisson
     * 使用异步接口，提高服务端吞吐量
     * Callable
     * DeferredResult
     */
    @GetMapping(value = "/lock", produces = "application/json")
    public Result redisLock() {
        // 获取redis分布式锁
        String lockKey = "lock_key";
        String requestId = UUID.randomUUID().toString();
        // 保证了setnx key与设置过期时间的原子性
        // 为锁设置了过期时间，保证了锁始终会被释放，避免持有锁的JVM因宕机未释放锁而产生deadlock
        Boolean success = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, requestId, 30, TimeUnit.SECONDS);
        // 获取锁失败，返回失败code
        if (!success) {
            return Result.error("系统繁忙");
        }
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
            // finally块 保证了JVM层面始终会被释放
            // 释放锁 保证加锁对象与释放锁对象的身份一致性
            if (Objects.equals(requestId, stringRedisTemplate.opsForValue().get(lockKey))) {
                stringRedisTemplate.delete(lockKey);
            }
        }
    }
}