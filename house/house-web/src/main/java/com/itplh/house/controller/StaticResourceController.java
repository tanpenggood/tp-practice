package com.itplh.house.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: tanpeng
 * @date: 2019-12-01 16:13
 * @version: v1.0.0
 */
@RestController
@RequestMapping(value = "/static")
public class StaticResourceController {

    @GetMapping(value = "/image")
    public String image() {
        return "image";
    }

    @GetMapping(value = "/video")
    public String video() {
        return "video";
    }
}
