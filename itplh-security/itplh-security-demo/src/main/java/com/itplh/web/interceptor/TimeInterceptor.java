package com.itplh.web.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-20 09:38
 * @version: v1.0.0
 */
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        System.out.println(((HandlerMethod) handler).getBean().getClass().getName() + "#" + ((HandlerMethod) handler).getMethod().getName());
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        Long startTime = (Long) request.getAttribute("startTime");
        System.out.println("time interceptor 耗时: " + (System.currentTimeMillis() - startTime));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
        System.out.println("afterCompletion");
        Long startTime = (Long) request.getAttribute("startTime");
        System.out.println("time interceptor 耗时: " + (System.currentTimeMillis() - startTime));
        System.out.println("ex is: " + e);
    }
}
