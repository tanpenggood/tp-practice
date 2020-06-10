package com.itplh.modules.wxjssdk.service;

import com.itplh.modules.wxjssdk.config.WxJsSdkEnum;
import com.itplh.modules.wxjssdk.pojo.response.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WxJsSdkService {

    @Autowired
    private RestTemplate restTemplate;

    public AccessTokenResponse getAccessToken() {
        String accessTokenUrl = String.format(WxJsSdkEnum.ACCESS_TOKEN_URL_TEMPLATE.getValue(),
                WxJsSdkEnum.APPID.getValue(), WxJsSdkEnum.SECRET.getValue());
        AccessTokenResponse accessTokenResponse = restTemplate.getForEntity(accessTokenUrl, AccessTokenResponse.class).getBody();
        return accessTokenResponse;
    }

}

