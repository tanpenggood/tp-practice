package com.itplh.security.uaa.service;

import com.alibaba.fastjson.JSON;
import com.itplh.security.uaa.dao.UserDao;
import com.itplh.security.uaa.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-06 14:20
 * @version: 1.0.0
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 登录账号
        System.out.println("username= " + username);
        // 根据账号去数据库查询...
        UserDO user = userDao.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        // 查询用角色
        List<String> roles = userDao.getRolesByUserId(user.getId());
        roles = roles.stream().map(roleName -> "ROLE_" + roleName).collect(Collectors.toList());
        // 查询用户权限
        List<String> permissions = userDao.getPermissionsByUserId(user.getId());
        // 合并角色、权限
        ArrayList<String> authorities = new ArrayList<>();
        authorities.addAll(roles);
        authorities.addAll(permissions);

        return User.withUsername(JSON.toJSONString(user))
                .password(user.getPassword())
                .authorities(authorities.toArray(new String[0]))
                .build();
    }

}
