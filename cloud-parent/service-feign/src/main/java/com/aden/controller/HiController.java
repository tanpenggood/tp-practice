package com.aden.controller;

import com.aden.feign.ScheduleServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: tanpeng
 * @date: 2019-12-04 10:49
 * @version: 1.0.0
 */
@RestController
public class HiController {

    @Autowired
    private ScheduleServiceHi scheduleServiceHi;

    /**
     * 启动程序，多次访问http://localhost:8765/hi?name=tanpeng,浏览器交替显示：
     *  hi tanpeng,i am from port:8762
     *  hi tanpeng,i am from port:8763
     *
     * 关闭service-hi工程，再访问http://localhost:8765/hi?name=tanpeng,网页显示：
     *  sorry tanpeng
     * 这证明断路器起到作用了
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        return scheduleServiceHi.sayHiFromClientOne(name);
    }
}
