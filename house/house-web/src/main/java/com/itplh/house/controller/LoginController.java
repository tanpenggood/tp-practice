package com.itplh.house.controller;

import com.itplh.house.common.constant.CommonConstants;
import com.itplh.house.common.model.User;
import com.itplh.house.interceptor.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @description:
 * @author: tanpeng
 * @date: 2019-12-01 17:06
 * @version: v1.0.0
 */
@RestController
public class LoginController {

    @Autowired
    private HttpSession session;

    @GetMapping(value = "/login")
    public ResponseEntity<User> login(@RequestParam(name = "username", defaultValue = "tanpeng") String username) {
        User user = User.builder().sessionId(session.getId()).name(username).loginTime(new Date()).build();
        session.setAttribute(CommonConstants.USER_ATTIIBUTE, user);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<User> logout() {
        User user = UserContext.getUser();
        session.invalidate();
        return ResponseEntity.ok(user);
    }
}
