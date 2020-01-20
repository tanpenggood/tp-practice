package com.aden.fallback;

import com.aden.feign.ScheduleServiceHi;
import org.springframework.stereotype.Component;

/**
 * @description: feign统一熔断处理
 * fallback
 *  仅提供服务降级，不能查看服务降级的具体原因
 * @author: tanpeng
 * @date: 2019-12-04 11:04
 * @version: 1.0.0
 */
@Component
public class ScheduleServiceHiHystrix implements ScheduleServiceHi {

    public String sayHiFromClientOne(String name) {
        return "sorry " + name;
    }
}
