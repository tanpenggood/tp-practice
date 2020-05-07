package com.itplh.security.model;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-07 10:13
 * @version: 1.0.0
 */
@Data
public class RoleDO {

    private String id;
    private String roleName;
    private String description;
    private Date createTime;
    private Date updateTime;
    private String status;

}
