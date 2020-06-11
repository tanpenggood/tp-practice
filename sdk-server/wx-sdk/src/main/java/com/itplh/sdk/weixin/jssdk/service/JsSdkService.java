package com.itplh.sdk.weixin.jssdk.service;

import com.itplh.sdk.weixin.jssdk.config.JsSdkEnum;
import com.itplh.sdk.weixin.jssdk.pojo.response.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JsSdkService {

    @Autowired
    private RestTemplate restTemplate;

    public AccessTokenResponse getAccessToken() {
        String accessTokenUrl = String.format(JsSdkEnum.ACCESS_TOKEN_URL_TEMPLATE.getValue(),
                JsSdkEnum.APPID.getValue(), JsSdkEnum.SECRET.getValue());
        AccessTokenResponse accessTokenResponse = restTemplate.getForEntity(accessTokenUrl, AccessTokenResponse.class).getBody();
        return accessTokenResponse;
    }

}

