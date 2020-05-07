package com.itplh.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 需要认证的资源
 * @author: tanpeng
 * @date: 2020-04-29 22:59
 * @version: v1.0.0
 */
@RestController
@RequestMapping("/r")
public class ResourceController {

    @GetMapping("/r1")
    public String r1(Authentication user) {
        return user.getName() + " 访问需要认证的资源r1";
    }

    @GetMapping("/r2")
    public String r2(Authentication user) {
        return user.getName() + " 访问需要认证的资源r2";
    }

    @GetMapping("/r3")
    public String r3(Authentication user) {
        return user.getName() + " 访问需要认证的资源r3";
    }

}
