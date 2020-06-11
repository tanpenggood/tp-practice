package com.itplh.sdk.weixin.jssdk.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWxJsSdkService {

    @Autowired
    private JsSdkService wxJsSdkService;

    @Test
    public void getAccessToken() {
        System.out.println(wxJsSdkService.getAccessToken());
    }

}
