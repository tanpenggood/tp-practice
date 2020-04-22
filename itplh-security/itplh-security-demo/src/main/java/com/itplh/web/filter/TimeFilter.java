package com.itplh.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-20 09:02
 * @version: v1.0.0
 */
@Slf4j
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("time filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("time filter start");
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        log.info("time filter 耗时: {}", System.currentTimeMillis() - start);
        log.info("time filter finish");
    }

    @Override
    public void destroy() {
        log.info("time filter destroy");
    }
}
