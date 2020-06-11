package com.itplh.sdk.weixin.jssdk.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author: tanpeng
 * @since: 2020-06-10 13:14
 */
@Data
@TableName("wx_js_sdk")
public class JsSdk {

    @TableId
    private Long id;
    private String accessToken;
    private String ticket;
    private Date createTime;

}
