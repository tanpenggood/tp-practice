package com.itplh.sdk.weixin.jssdk.mapper;

import com.itplh.sdk.common.util.IdWorker;
import com.itplh.sdk.weixin.jssdk.pojo.entity.JsSdk;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWxJsSdkMapper {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private JsSdkMapper wxJsSdkMapper;

    @Test
    public void insert() {
        long nextId = idWorker.nextId();
        JsSdk wxJsSdk = new JsSdk();
        wxJsSdk.setId(nextId);
        wxJsSdk.setAccessToken("access_token" + nextId);
        wxJsSdk.setTicket("ticket" + nextId);
        wxJsSdk.setCreateTime(new Date());
        wxJsSdkMapper.insert(wxJsSdk);
        System.out.println(wxJsSdkMapper.selectById(nextId));
    }

}
