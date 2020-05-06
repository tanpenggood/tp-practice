package com.itplh.security.model;

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
    private String password;
    private String fullname;
    private String mobile;

}
