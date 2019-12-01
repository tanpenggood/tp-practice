package com.itplh.house.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: tanpeng
 * @date: 2019-12-01 17:29
 * @version: v1.0.0
 */
@RestController
public class IndexController {

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }
}
