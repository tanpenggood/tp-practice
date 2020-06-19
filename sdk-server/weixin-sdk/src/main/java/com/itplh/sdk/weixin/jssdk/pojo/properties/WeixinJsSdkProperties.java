package com.itplh.sdk.weixin.jssdk.pojo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "weixin.jssdk")
public class WeixinJsSdkProperties {

    /**
     * appid
     */
    private String appid;
    /**
     * secret
     */
    private String secret;
    /**
     * 基本配置中的token
     */
    private String basicConfigToken;
    /**
     * access_token和ticket的过期时间
     */
    private int expiresIn = 7000;
    /**
     * 申请access_token的url
     */
    private String urlTemplateAccessToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    /**
     * 申请ticket的url
     */
    private String urlTemplateTicket = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";

}
