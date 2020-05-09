package com.itplh.security.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 认证管理器
 * 密码编码器
 * 安全拦截机制
 * @description:
 * @author: tanpeng
 * @date: 2020-04-29 22:40
 * @version: v1.0.0
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @description: 密码编码器
     * @author: tanpeng
     * @date : 2020-04-29 23:36
     * @version: v1.0.0
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
            .and().authorizeRequests()
                .antMatchers("/r/r1").hasAuthority("p1")
                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/r3").access("hasAuthority('p1') and hasAuthority('p2')")
                .antMatchers("/login*").permitAll()
                .anyRequest().authenticated()
            .and().csrf().disable();
    }

}
