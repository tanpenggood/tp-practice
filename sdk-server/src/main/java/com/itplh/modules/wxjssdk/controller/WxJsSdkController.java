package com.itplh.modules.wxjssdk.controller;

import com.itplh.common.Result;
import com.itplh.modules.wxjssdk.mapper.WxJsSdkMapper;
import com.itplh.modules.wxjssdk.pojo.entity.WxJsSdk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tanpeng
 * @since: 2020-06-10 13:18
 */
@RestController
@RequestMapping("/wx-js-sdk")
public class WxJsSdkController {

    @Autowired
    private WxJsSdkMapper wxJsSdkMapper;

    @GetMapping
    public Result get() {
        WxJsSdk wxJsSdk = wxJsSdkMapper.selectById(1);
        return Result.ok(wxJsSdk);
    }
}
