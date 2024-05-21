package com.HuangYufan.filter;

import javax.servlet.*;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("i am in AuthFilter before");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("i am in AuthFilter after");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
