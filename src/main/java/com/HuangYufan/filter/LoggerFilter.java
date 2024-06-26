package com.HuangYufan.filter;

import javax.servlet.*;
import java.io.IOException;

public class LoggerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("i am in LoggerFilter before");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("i am in LoggerFilter after");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
