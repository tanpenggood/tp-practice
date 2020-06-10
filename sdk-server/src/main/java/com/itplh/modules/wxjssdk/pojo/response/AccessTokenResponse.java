package com.itplh.modules.wxjssdk.pojo.response;

import lombok.Data;

@Data
public class AccessTokenResponse {
    private String access_token;
    private int expires_in;
}
