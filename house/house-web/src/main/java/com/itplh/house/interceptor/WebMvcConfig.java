package com.itplh.house.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @author: tanpeng
 * @date: 2019-12-01 12:38
 * @version: v1.0.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private AuthActionInterceptor authActionInterceptor;

    /**
     * @description: 控制拦截器拦截的顺序
     * @author: tanpeng
     * @date : 2019-12-01 17:26
     * @version: v1.0.0
     * @param registry 1
     * @return : void
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截除静态资源外的所有请求
        registry.addInterceptor(authInterceptor).excludePathPatterns("/static").addPathPatterns("/**");
        // 只拦截需要登陆的请求
        registry.addInterceptor(authActionInterceptor).addPathPatterns("/user/info");
    }
}
