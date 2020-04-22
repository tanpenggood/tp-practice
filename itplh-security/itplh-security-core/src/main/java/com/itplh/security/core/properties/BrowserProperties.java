package com.itplh.security.core.properties;

import lombok.Data;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-22 12:51
 * @version: 1.0.0
 */
@Data
public class BrowserProperties {

    private String loginPage = "/sign-in.html";
    private LoginResponseType loginType = LoginResponseType.JSON;

}
