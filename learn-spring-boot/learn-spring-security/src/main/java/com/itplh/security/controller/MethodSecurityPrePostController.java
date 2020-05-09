package com.itplh.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * Spring Security 支持的所有SpEL表达式如下：
 *      authentication　　	用户认证对象
 *      hasRole(role)	如果用户被授予了指定的角色，结果为true
 *      hasAnyRole(list of roles) 如果用户拥有指定的任意角色，结果为true
 *      hasAuthority(authority) 如果用户被授予了指定的权限，结果为true
 *      hasAnyAuthority(list of authorities) 如果用户被授予了指定的权限，结果为true
 *      hasIpAddress(IP Address)	用户地址
 *      isAnonymous() 匿名用户，结果为true
 *      isAuthenticated() 已认证用户，结果为true
 *      isFullyAuthenticated　　	不是匿名也不是remember-me认证
 *      isRememberMe()　　	remember-me认证
 *      permitAll	始终true
 *      denyAll　　	结果始终为false
 *      principal	用户主要信息对象
 * @author: tanpeng
 * @date: 2020-05-07 11:14
 * @version: 1.0.0
 */
@RestController
public class MethodSecurityPrePostController {

    @GetMapping("/p1")
    @PreAuthorize("permitAll()")
    public String p1() {
        return "p1 所有人可访问";
    }

    @GetMapping("/p2")
    @PreAuthorize("isAnonymous()")
    public String p2() {
        return "p2 匿名用户可访问";
    }

    @GetMapping("/p3")
    @PreAuthorize("hasAuthority('p1') and hasAuthority('p2')")
    public String p3() {
        return "p3 需要同时拥有p1和p2权限才能访问";
    }

}
