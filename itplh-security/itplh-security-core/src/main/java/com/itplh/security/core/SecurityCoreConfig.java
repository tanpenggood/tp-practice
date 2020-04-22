package com.itplh.security.core;

import com.itplh.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-22 12:53
 * @version: 1.0.0
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
