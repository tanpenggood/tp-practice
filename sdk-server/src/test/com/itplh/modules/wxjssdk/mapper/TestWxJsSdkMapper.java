package com.itplh.modules.wxjssdk.mapper;

import com.itplh.common.util.IdWorker;
import com.itplh.modules.wxjssdk.pojo.entity.WxJsSdk;
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
    private WxJsSdkMapper wxJsSdkMapper;

    @Test
    public void insert() {
        long nextId = idWorker.nextId();
        WxJsSdk wxJsSdk = new WxJsSdk();
        wxJsSdk.setId(nextId);
        wxJsSdk.setAccessToken("access_token" + nextId);
        wxJsSdk.setTicket("ticket" + nextId);
        wxJsSdk.setCreateTime(new Date());
        wxJsSdkMapper.insert(wxJsSdk);
        System.out.println(wxJsSdkMapper.selectById(nextId));
    }

}
