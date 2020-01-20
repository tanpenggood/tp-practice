package com.aden.feign;

import com.aden.fallback.ScheduleServiceHiFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * fallback
 *  仅提供服务降级，不能查看服务降级的具体原因
 *
 * fallbackFactory
 *  1、提供服务降级
 *  2、提供服务调用异常或失败的具体原因
 * @author: tanpeng
 * @date: 2019-12-04 10:45
 * @version: 1.0.0
 */
//@FeignClient(value = "service-hi", fallback = ScheduleServiceHiHystrix.class)
@FeignClient(value = "service-hi", fallbackFactory = ScheduleServiceHiFallbackFactory.class)
public interface ScheduleServiceHi {

    @GetMapping("/hi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
