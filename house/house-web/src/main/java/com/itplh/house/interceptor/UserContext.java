package com.itplh.house.interceptor;

import com.itplh.house.common.model.User;

/**
 * @description:
 * @author: tanpeng
 * @date: 2019-12-01 16:51
 * @version: v1.0.0
 */
public class UserContext {

    private static final ThreadLocal<User> USER_HOLDER = new ThreadLocal<>();

    public static void setUser(User user) {
        USER_HOLDER.set(user);
    }

    public static void remove() {
        USER_HOLDER.remove();
    }

    public static User getUser() {
        return USER_HOLDER.get();
    }
}
