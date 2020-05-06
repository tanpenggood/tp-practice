package com.itplh.security.dao;

import com.itplh.security.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-06 14:12
 * @version: 1.0.0
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDO getUserByUsername(String username) {
        String sql = "select id,username,password,fullname from t_user where username = ?";
        List<UserDO> list = jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(UserDO.class));
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }

}
