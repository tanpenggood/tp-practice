package com.itplh.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * @description:
 * 1 声明切片类
 * 2 切入点(注解)
 *      a 在哪些方法上起作用
 *      b 在什么时候起作用
 * 3 增强(方法)
 *      起作用时执行的业务逻辑
 * @author: tanpeng
 * @date: 2020-03-20 10:26
 * @version: v1.0.0
 */
@Aspect
@Component
@Slf4j
public class TimeAspect {

    @Around("execution(public * com.itplh.web.controller.*Controller.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint point) throws Throwable {
        log.info("time aspect start");
        Stream.of(point.getArgs()).forEach(arg -> log.info("arg is: {}", arg));
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        log.info("time aspect 耗时: " + (System.currentTimeMillis() - start));
        log.info("time aspect end");
        return result;
    }

}
