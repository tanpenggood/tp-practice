package com.itplh.modules.wxjssdk.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author: tanpeng
 * @since: 2020-06-10 13:14
 */
@Data
public class WxJsSdk {

    @TableId
    private Long id;
    private String accessToken;
    private String ticket;
    private Date createTime;

}
