package com.itplh.sdk.weixin.jssdk.pojo.bo;

import lombok.Data;

@Data
public class SignatureBO {

    private String appId;
    private String noncestr;
    private String timestamp;
    private String signature;

}
