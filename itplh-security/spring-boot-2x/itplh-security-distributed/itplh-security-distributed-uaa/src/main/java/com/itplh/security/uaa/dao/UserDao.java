package com.itplh.security.uaa.dao;

import com.itplh.security.uaa.model.PermissionDO;
import com.itplh.security.uaa.model.RoleDO;
import com.itplh.security.uaa.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据用户名查询用户对象 {@link #getUserByUsername}
 * 根据用户id查询用户角色列表 {@link #getRolesByUserId}
 * 根据用户id查询用户权限列表 {@link #getPermissionsByUserId}
 * @description:
 * @author: tanpeng
 * @date : 2020/5/8 15:25
 * @version: v1.0.0
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

    /**
     * @description: 根据用户id查询用户角色
     * @author: tanpeng
     * @date : 2020/5/7 11:35
     * @version: v1.0.0
     */
    public List<String> getRolesByUserId(String userId) {
        String sql = new StringBuilder()
                .append("SELECT * FROM t_role WHERE id IN(")
                    .append("SELECT role_id FROM t_user_role WHERE user_id = ?")
                .append(")").toString();
        List<RoleDO> list = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(RoleDO.class));
        List<String> roles = new ArrayList<>();
        list.forEach(c -> roles.add(c.getRoleName()));
        return roles;
    }

    /**
     * @description: 根据用户id查询用户权限
     * @author: tanpeng
     * @date : 2020/5/7 10:12
     * @version: v1.0.0
     */
    public List<String> getPermissionsByUserId(String userId) {
        String sql = new StringBuilder()
                .append("SELECT * FROM t_permission WHERE id IN(")
                    .append("SELECT permission_id FROM t_role_permission WHERE role_id IN(")
                        .append("SELECT role_id FROM t_user_role WHERE user_id = ?")
                    .append(")")
                .append(")").toString();
        List<PermissionDO> list = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(PermissionDO.class));
        List<String> permissions = new ArrayList<>();
        list.forEach(c -> permissions.add(c.getCode()));
        return permissions;
    }

}
