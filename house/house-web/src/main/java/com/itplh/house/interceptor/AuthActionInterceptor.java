package com.itplh.house.interceptor;

import com.itplh.house.common.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @description: 未登录则重定向到index
 * @author: tanpeng
 * @date: 2019-12-01 12:32
 * @version: v1.0.0
 */
@Component
public class AuthActionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("*****************AuthActionInterceptor " + request.getRequestURI());
        User user = UserContext.getUser();
        if (user == null) {
            String msg = URLEncoder.encode("请先登陆", "UTF-8");
            String target = URLEncoder.encode(request.getRequestURI(), "UTF-8");
            if ("GET".equalsIgnoreCase(request.getMethod())) {
                response.sendRedirect("/index?errorMsg=" + msg + "&target=" + target);
            } else {
                response.sendRedirect("/index?errorMsg=" + msg);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
