package com.itplh.sdk.weixin.jssdk.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWeixinJsSdkService {

    @Autowired
    private WeixinJsSdkService weixinJsSdkService;

    @Test
    public void getAccessToken() {
        System.out.println(weixinJsSdkService.getAccessToken());
    }

    @Test
    public void getTicket() {
        System.out.println(weixinJsSdkService.getTicket());
    }

}
