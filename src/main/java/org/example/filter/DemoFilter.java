package org.example.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")//拦截所有请求
@Slf4j
public class DemoFilter implements Filter {
    //初始化,web服务启动时执行，只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 初始化方法 ......");
    }

    //过滤方法,每次请求时执行
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("拦截到了请求 ...");
        //放行
        filterChain.doFilter(servletRequest, servletResponse);

        log.info("拦截到了请求 ...  放行后");
    }

    //销毁,web服务停止时执行，只执行一次
    @Override
    public void destroy() {
        log.info("destroy 销毁方法 ......");
    }
}
