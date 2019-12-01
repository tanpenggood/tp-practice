package com.itplh.house.interceptor;

import com.itplh.house.common.constant.CommonConstants;
import com.itplh.house.common.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @description: 统一处理user对象
 * 请求开始时将user对象放入ThreadLocal
 * 请求结束时将user对象从ThreadLocal移除
 * @author: tanpeng
 * @date: 2019-12-01 12:37
 * @version: v1.0.0
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("------------------AuthInterceptor " + request.getRequestURI());
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/static") || requestURI.startsWith("/error")) {
            return true;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(CommonConstants.USER_ATTIIBUTE);
        if (user != null) {
            UserContext.setUser(user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.remove();
    }
}
