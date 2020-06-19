package com.itplh.sdk.weixin.jssdk.mapper;

import com.itplh.sdk.common.util.IdWorker;
import com.itplh.sdk.weixin.jssdk.pojo.entity.WeixinJsSdk;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWeixinJsSdkMapper {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private WeixinJsSdkMapper weixinJsSdkMapper;

    @Test
    public void insert() {
        long nextId = idWorker.nextId();
        WeixinJsSdk weixinJsSdk = new WeixinJsSdk();
        weixinJsSdk.setId(nextId);
        weixinJsSdk.setAccessToken("access_token" + nextId);
        weixinJsSdk.setTicket("ticket" + nextId);
        weixinJsSdk.setCreateTime(new Date());
        weixinJsSdkMapper.insert(weixinJsSdk);
        System.out.println(weixinJsSdkMapper.selectById(nextId));
    }

}
