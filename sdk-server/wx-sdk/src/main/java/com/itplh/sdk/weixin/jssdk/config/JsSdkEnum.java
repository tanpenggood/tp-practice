package com.itplh.sdk.weixin.jssdk.config;

import lombok.Getter;

@Getter
public enum JsSdkEnum {

    TOKEN("TOKEN"),
    APPID("APPID"),
    SECRET("SECRET"),
    EXPIRES_IN("7000"),
    ACCESS_TOKEN_URL_TEMPLATE("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s"),
    TICKET_URL_TEMPLATE("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi");

    private String value;

    JsSdkEnum(String value) {
        this.value = value;
    }
}
