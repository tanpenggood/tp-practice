package com.itplh.security.service;

import com.itplh.security.dao.UserDao;
import com.itplh.security.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        //登录账号
        System.out.println("username= " + username);
        //根据账号去数据库查询...
        UserDO user = userDao.getUserByUsername(username);
        return user == null ? null : User.withUsername(user.getFullname()).password(user.getPassword()).authorities("p1").build();
    }
}
