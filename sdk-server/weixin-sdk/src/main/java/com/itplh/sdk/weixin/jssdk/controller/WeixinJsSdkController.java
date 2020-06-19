package com.itplh.sdk.weixin.jssdk.controller;

import com.itplh.sdk.common.Result;
import com.itplh.sdk.weixin.jssdk.pojo.properties.WeixinJsSdkProperties;
import com.itplh.sdk.weixin.jssdk.pojo.ro.ConnectAuthRO;
import com.itplh.sdk.weixin.jssdk.service.WeixinJsSdkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author: tanpeng
 * @since: 2020-06-10 13:18
 */
@Slf4j
@RestController
@RequestMapping("/weixin/js-sdk")
public class WeixinJsSdkController {

    @Autowired
    private WeixinJsSdkService jsSdkService;

    @Autowired
    private WeixinJsSdkProperties jsSdkProperties;

    @GetMapping
    public String connectAuth(@Valid ConnectAuthRO connectAuthRO) throws NoSuchAlgorithmException {
        String signature = connectAuthRO.getSignature();
        String hashcode = connectAuthRO.hashcodeBySha1(jsSdkProperties.getBasicConfigToken());
        log.info("signature: {}", signature);
        log.info("hashcode: {}", hashcode);
        log.info(connectAuthRO.toString());
        if (Objects.equals(signature, hashcode)) {
            return connectAuthRO.getEchostr();
        }
        return null;
    }

    @GetMapping("/signature")
    public Result signature(String url) {
        log.info("url {}", url);
        if (StringUtils.isEmpty(url)) {
            return Result.error();
        }
        return Result.ok(jsSdkService.getSignatureBO(url));
    }

    @Bean("com.itplh.sdk.weixin.jssdk.controller.JsSdkController.commandLineRunner")
    CommandLineRunner commandLineRunner() {
        return args -> log.info(jsSdkProperties.toString());
    }

}
