package com.itplh.security.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-09 14:41
 * @version: 1.0.0
 */
@Configuration
public class TokenConfig {

    /**
     * 资源服务令牌解析服务
     *
     * @description:
     * @author: tanpeng
     * @date : 2020/5/9 14:38
     * @version: v1.0.0
     */
    @Bean
    public ResourceServerTokenServices resourceServerTokenServices() {
        // 使用远程服务请求授权服务器校验token,必须指定校验token 的url、client_id，client_secret
        RemoteTokenServices service = new RemoteTokenServices();
        service.setCheckTokenEndpointUrl("http://localhost:53020/uaa/oauth/check_token");
        service.setClientId("c1");
        service.setClientSecret("secret");
        return service;
    }

}
