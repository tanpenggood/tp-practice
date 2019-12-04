package com.aden.controller;

import com.aden.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: tanpeng
 * @date: 2019-12-04 10:35
 * @version: 1.0.0
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    /**
     * 启动：service-ribbon 工程，当我们访问http://localhost:8767/hi?name=tanpeng,浏览器显示：
     *  hi tanpeng,i am from port:8762
     * 此时关闭 service-hi 工程，当我们再访问http://localhost:8767/hi?name=tanpeng，浏览器会显示：
     *  hi ,tanpeng,orry,error!
     *
     * 这就说明当 service-hi 工程不可用的时候，service-ribbon调用 service-hi的API接口时，会执行快速失败，直接返回一组字符串，
     * 而不是等待响应超时，这很好的控制了容器的线程阻塞。
     * @param name
     * @return
     */
    @GetMapping(value = "/hi")
    public String hi(@RequestParam(value = "name") String name) {
        return helloService.hiService(name);
    }
}
