package com.aden.fallback;

import com.aden.feign.ScheduleServiceHi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @description: feign统一熔断处理
 * fallbackFactory
 *  1、提供服务降级
 *  2、提供服务调用异常或失败的具体原因
 * @author: tanpeng
 * @date: 2020-01-20 9:49
 * @version: 1.0.0
 */
@Component
public class ScheduleServiceHiFallbackFactory implements FallbackFactory<ScheduleServiceHi> {

    public ScheduleServiceHi create(final Throwable throwable) {
        return new ScheduleServiceHi() {
            public String sayHiFromClientOne(String name) {
                return new StringBuilder("sorry ")
                        .append(name)
                        .append("<br/> error:")
                        .append(throwable.getMessage())
                        .toString();
            }
        };
    }
}
