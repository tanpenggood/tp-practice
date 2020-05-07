package com.itplh.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-07 11:14
 * @version: 1.0.0
 */
@RestController
public class MethodSecuritySecuredController {

    @GetMapping("/s1")
    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    public String s1() {
        return "s1 可匿名访问";
    }

    @GetMapping("/s2")
    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    public String s2() {
        return "s1 可匿名访问";
    }

    @GetMapping("/s3")
    @Secured("ROLE_管理员")
    public String s3() {
        return "s3 需要有管理员角色才能访问";
    }

}
