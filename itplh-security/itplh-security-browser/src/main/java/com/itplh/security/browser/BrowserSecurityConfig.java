package com.itplh.security.browser;

import com.itplh.security.browser.authentication.ItplhAuthenticationFailureHandler;
import com.itplh.security.browser.authentication.ItplhAuthenticationSuccessHandler;
import com.itplh.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-22 11:07
 * @version: 1.0.0
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ItplhAuthenticationFailureHandler itplhAuthenticationFailureHandler;
    @Autowired
    private ItplhAuthenticationSuccessHandler itplhAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
//                .loginPage("/sign-in.html") // 自定义登陆表单
                .loginPage("/authentication/require") // 自定义用户认证逻辑
                .loginProcessingUrl("/authentication/form")
                .successHandler(itplhAuthenticationSuccessHandler) // 自定义成功处理器
                .failureHandler(itplhAuthenticationFailureHandler) // 自定义失败处理器
//        http.httpBasic()
            .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/image"
                        ).permitAll()
                .anyRequest()
                .authenticated()
            .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
