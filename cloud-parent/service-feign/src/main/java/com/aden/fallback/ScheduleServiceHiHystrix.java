package com.aden.fallback;

import com.aden.feign.ScheduleServiceHi;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tanpeng
 * @date: 2019-12-04 11:04
 * @version: 1.0.0
 */
@Component
public class ScheduleServiceHiHystrix implements ScheduleServiceHi {

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry " + name;
    }
}
