package com.aden.controller;

import com.aden.service.HelloService;
import com.netflix.discovery.converters.Auto;
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
     * 在浏览器上多次访问http://localhost:8764/hi?name=tanpeng，浏览器交替显示：
     *  hi tanpeng,i am from port:8762
     *  hi tanpeng,i am from port:8763
     * 这说明当我们通过调用restTemplate.getForObject(“http://SERVICE-HI/hi?name=”+name,String.class)方法时，
     * 已经做了负载均衡，访问了不同的端口的服务实例。
     * @param name
     * @return
     */
    @GetMapping(value = "/hi")
    public String hi(@RequestParam(value = "name") String name) {
        return helloService.hiService(name);
    }
}
