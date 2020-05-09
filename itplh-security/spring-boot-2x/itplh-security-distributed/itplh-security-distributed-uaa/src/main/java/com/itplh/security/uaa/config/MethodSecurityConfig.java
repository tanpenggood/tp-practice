package com.itplh.security.uaa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 开启方法授权
 * @description:
 * @author: tanpeng
 * @date: 2020-05-07 11:05
 * @version: 1.0.0
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class MethodSecurityConfig {
}
