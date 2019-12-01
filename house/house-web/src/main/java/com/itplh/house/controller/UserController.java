package com.itplh.house.controller;

import com.itplh.house.common.model.User;
import com.itplh.house.interceptor.UserContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: tanpeng
 * @date: 2019-12-01 16:18
 * @version: v1.0.0
 */
@RequestMapping(value = "/user")
@RestController
public class UserController {

    @GetMapping(value = "/info")
    public ResponseEntity<User> userInfo() {
        return ResponseEntity.ok(UserContext.getUser());
    }
}
