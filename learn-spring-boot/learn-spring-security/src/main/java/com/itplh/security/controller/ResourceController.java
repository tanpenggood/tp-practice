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

    @GetMapping("/p1")
    public String p1(Authentication user) {
        return user.getName() + " 访问需要认证的资源p1";
    }

    @GetMapping("/p2")
    public String p2(Authentication user) {
        return user.getName() + " 访问需要认证的资源p2";
    }

}
