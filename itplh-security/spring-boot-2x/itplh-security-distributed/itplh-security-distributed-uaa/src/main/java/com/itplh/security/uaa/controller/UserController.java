package com.itplh.security.uaa.controller;

import com.alibaba.fastjson.JSON;
import com.itplh.security.uaa.model.UserDO;
import com.itplh.security.uaa.service.MyUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-09 11:55
 * @version: 1.0.0
 */
@RestController
public class UserController {

    /**
     * 获取当前用户信息
     * {@link MyUserDetailsService#loadUserByUsername}中封装在username字段中的数据
     * @description:
     * @author: tanpeng
     * @date : 2020/5/9 13:18
     * @version: v1.0.0
     */
    @GetMapping("/user")
    public String user(Authentication user) {
        UserDO userDO = JSON.parseObject(user.getName(), UserDO.class);
        return JSON.toJSONString(userDO);
    }
}
