package com.itplh.security.uaa.model;

import lombok.Data;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-07 10:13
 * @version: 1.0.0
 */
@Data
public class PermissionDO {

    private String id;
    private String code;
    private String description;
    private String url;

}
