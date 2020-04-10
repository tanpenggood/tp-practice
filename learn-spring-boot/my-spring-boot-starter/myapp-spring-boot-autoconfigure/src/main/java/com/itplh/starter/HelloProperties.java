package com.itplh.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description: properties bean
 * 会从 application.properties 或 yml中把对应的配置映射到 bean 中
 * @author: tanpeng
 * @date: 2020-04-10 15:10
 * @version: 1.0.0
 */
@ConfigurationProperties(prefix = "myapp.hello")
public class HelloProperties {

    private String suffix = "下午好(默认值)";

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
