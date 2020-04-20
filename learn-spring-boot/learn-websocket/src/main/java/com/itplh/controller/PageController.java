package com.itplh.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-20 11:06
 * @version: 1.0.0
 */
@RestController
public class PageController {

    @GetMapping
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping(value = "/index")
    public ModelAndView index(@RequestParam(value = "username") String username) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(username)) {
            username = "匿名用户";
        }
        ModelAndView index = new ModelAndView("index");
        index.addObject("username", username);
        index.addObject("webSocketUrl", "ws://localhost:9999/chat/" + URLEncoder.encode(username, "UTF-8"));
        return index;
    }

}
