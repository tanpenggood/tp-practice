package com.itplh.controller;

import com.itplh.starter.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-10 15:27
 * @version: 1.0.0
 */
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name", defaultValue = "tanpeng", required = false) String name) {
        return helloService.sayHello(name);
    }

}
