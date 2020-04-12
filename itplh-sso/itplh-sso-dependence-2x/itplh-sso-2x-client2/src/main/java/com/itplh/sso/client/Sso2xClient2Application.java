package com.itplh.sso.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-29 00:17
 * @version: v1.0.0
 */
@SpringBootApplication
@RestController
public class Sso2xClient2Application {

    public static void main(String[] args) {
        SpringApplication.run(Sso2xClient2Application.class, args);
    }

    @GetMapping("/user")
    public Authentication user(Authentication user) {
        return user;
    }

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
