package com.itplh.modules.wxjssdk.controller;

import com.itplh.modules.wxjssdk.pojo.ro.ConnectAuthRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author: tanpeng
 * @since: 2020-06-10 13:18
 */
@Slf4j
@RestController
@RequestMapping("/wx-js-sdk")
public class WxJsSdkController {

    @GetMapping
    public String connectTest(ConnectAuthRO connectAuthRO) throws NoSuchAlgorithmException {
        String signature = connectAuthRO.getSignature();
        String hashcode = connectAuthRO.hashcodeBySha1();
        log.info("signature: {}", signature);
        log.info("hashcode: {}", hashcode);
        log.info(connectAuthRO.toString());
        if (Objects.equals(signature, hashcode)) {
            return connectAuthRO.getEchostr();
        }
        return null;
    }

}
