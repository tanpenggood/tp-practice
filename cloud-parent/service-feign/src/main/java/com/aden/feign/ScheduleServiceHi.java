package com.aden.feign;

import com.aden.fallback.ScheduleServiceHiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: tanpeng
 * @date: 2019-12-04 10:45
 * @version: 1.0.0
 */
@FeignClient(value = "service-hi", fallback = ScheduleServiceHiHystrix.class)
public interface ScheduleServiceHi {

    @GetMapping("/hi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
