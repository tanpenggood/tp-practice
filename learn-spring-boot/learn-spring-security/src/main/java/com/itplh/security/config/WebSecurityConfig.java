package com.itplh.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-29 22:40
 * @version: v1.0.0
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @description: 在内存中定义用户信息
     * @author: tanpeng
     * @date : 2020-04-29 23:35
     * @version: v1.0.0
     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        userDetailsManager.createUser(User.withUsername("tanpeng").password("111").authorities("p1").build());
//        userDetailsManager.createUser(User.withUsername("huahua").password("222").authorities("p2").build());
//        return userDetailsManager;
//    }

    /**
     * @description: 密码编码器
     * @author: tanpeng
     * @date : 2020-04-29 23:36
     * @version: v1.0.0
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * @description: 安全拦截机制
     * @author: tanpeng
     * @date : 2020-04-29 23:36
     * @version: v1.0.0
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successForwardUrl("/login-success")
                .and().authorizeRequests()
                .antMatchers("/r/**").authenticated()
                .anyRequest().permitAll();
    }

}
