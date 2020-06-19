package com.itplh.sdk.weixin.jssdk.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author: tanpeng
 * @since: 2020-06-10 13:14
 */
@Data
public class WeixinJsSdk {

    @TableId
    private Long id;
    private String accessToken;
    private String ticket;
    private Date createTime;

}
