package com.itplh.sdk.weixin.jssdk.pojo.bo;

import lombok.Data;

@Data
public class SignatureBO {

    private String appId;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 时间戳(秒)
     */
    private String timestamp;
    private String signature;

}
