package com.itplh.security.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-09 11:22
 * @version: 1.0.0
 */
@RestController
public class UserController {

    /**
     * 获取当前用户信息
     * @description:
     * @author: tanpeng
     * @date : 2020/5/9 13:18
     * @version: v1.0.0
     */
    @GetMapping(value = "/me")
    public String user(Authentication user) {
        return user.getName();
    }

    @GetMapping(value = "/r1")
    @PreAuthorize("hasAuthority('p1')")
    public String r1(){
        return "访问资源1 需要p1权限";
    }

    @GetMapping(value = "/r2")
    @PreAuthorize("hasAuthority('p2')")
    public String r2(){
        return "访问资源2 需要权限p2";
    }

    @GetMapping(value = "/r3")
    @PreAuthorize("hasAuthority('p1') and hasAuthority('p2')")
    public String r3(){
        return "访问资源3 需要权限: p1 and p2";
    }

}
