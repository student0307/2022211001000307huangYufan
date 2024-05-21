package com.HuangYufan.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
//@WebFilter("/hello") 7 吧吧
public class HelloFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("i am in hello Filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("i am in doFilter before");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("i am in do Filter response come back ");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("i am in Filter destroy");
    }
}
