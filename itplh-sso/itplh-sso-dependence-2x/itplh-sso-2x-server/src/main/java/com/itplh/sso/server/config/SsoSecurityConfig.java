package com.itplh.sso.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-29 20:53
 * @version: v1.0.0
 */
@Configuration
public class SsoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 在内存中定义用户信息
     * @description:
     * @author: tanpeng
     * @date : 2020/5/13 11:45
     * @version: v1.0.0
     */
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("su").password(passwordEncoder.encode("111")).authorities("p1").build());
        userDetailsManager.createUser(User.withUsername("huan").password(passwordEncoder.encode("222")).authorities("p2").build());
        return userDetailsManager;
    }

    /**
     * 通过实现UserDetailsService重写loadUserByUsername来获取用户信息，可用于从数据库查询用户信息
     * @description:
     * @author: tanpeng
     * @date : 2020/5/13 11:45
     * @version: v1.0.0
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//    }

}
