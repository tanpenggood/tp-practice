//package com.aden;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @description:
// * @author: tanpeng
// * @date: 2020-01-20 15:21
// * @version: 1.0.0
// */
//@EnableWebSecurity
//public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
//    /**
//     * Eureka Server默认开启了CsrfFilter，导致微服务不能注册成功。
//     * 因此需要关闭Eureka Server的CsrfFilter
//     *
//     * @author: tanpeng
//     * @date : 2020/1/20 15:23
//     * @version: v1.0.0
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 关闭csrf
//        http.csrf().disable();
//        super.configure(http);
//    }
//}
