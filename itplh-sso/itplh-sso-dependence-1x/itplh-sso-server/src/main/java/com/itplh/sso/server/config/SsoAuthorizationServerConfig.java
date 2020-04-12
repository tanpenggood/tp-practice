package com.itplh.sso.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-29 00:01
 * @version: v1.0.0
 */
@Configuration
@EnableAuthorizationServer
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("itplh-client1")
                .secret("itplh-client-secret1")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                // error="invalid_request", error_description="At least one redirect_uri must be registered with the client."
                .redirectUris("http://client1.itplh.com:8081/login")
                .scopes("all")
                // 自动授权
                .autoApprove(true)
            .and()
                .withClient("itplh-client2")
                .secret("itplh-client-secret2")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                // error="invalid_request", error_description="At least one redirect_uri must be registered with the client."
                .redirectUris("http://client2.itplh.com:8082/login")
                .scopes("all")
                .autoApprove(true);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jwtTokenStore())
                .accessTokenConverter(jwtAccessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("isAuthenticated()");
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("itplh");
        return converter;
    }
}
