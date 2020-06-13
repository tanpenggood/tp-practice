package com.itplh.sdk.weixin.jssdk.pojo.response;

import lombok.Data;

@Data
public class TicketResponse {

    private int errcode;
    private String errmsg;
    private String ticket;
    private int expires_in;

}
