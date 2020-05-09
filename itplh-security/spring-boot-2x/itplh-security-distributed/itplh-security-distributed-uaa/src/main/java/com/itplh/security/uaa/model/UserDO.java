package com.itplh.security.uaa.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-06 14:07
 * @version: 1.0.0
 */
@Data
public class UserDO {

    private String id;
    private String username;
    @JSONField(serialize = false)
    private String password;
    private String fullname;
    private String mobile;

}
