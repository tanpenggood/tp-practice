package com.itplh.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-29 23:36
 * @version: v1.0.0
 */
@Controller
public class LoginSuccessController {

    @RequestMapping("/login-success")
    public ModelAndView success() {
        return new ModelAndView("login-success");
    }

}
