package com.itplh.security.core.validate.code;

import com.itplh.security.core.properties.SecurityProperties;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-22 15:42
 * @version: 1.0.0
 */
@Data
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private AuthenticationFailureHandler authenticationFailureHandler;
    private SecurityProperties securityProperties;
    private Set<String> urls = new HashSet<>();
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urls.addAll(securityProperties.getCode().getImage().getUrl());
        urls.add("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        boolean action = false;
        for(String url: urls){
            if (antPathMatcher.match(url, request.getRequestURI())) {
                action = true;
                break;
            }
        }
        if (action) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeInSession.isExpire()) {
            sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equals(codeInRequest, codeInSession.getCode())) {
            throw new ValidateCodeException("验证码不正确");
        }
        sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
    }

}
