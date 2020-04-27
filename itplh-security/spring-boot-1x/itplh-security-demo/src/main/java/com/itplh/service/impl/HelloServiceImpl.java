package com.itplh.service.impl;

import com.itplh.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-19 18:24
 * @version: v1.0.0
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public void greeting(String name) {
        System.out.println("hello " + name);
    }
}
