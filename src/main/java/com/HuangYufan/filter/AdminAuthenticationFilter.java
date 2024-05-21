package com.HuangYufan.filter;


import com.HuangYufan.model.users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")//this filter for all admin url
public class AdminAuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain Chain) throws IOException, ServletException {
        //change ServletRequest to HttpServletRequest
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        //get session
        HttpSession session = httpRequest.getSession(false);
        if (session!=null && session.getAttribute("user")!=null) {
            users user= (users) session.getAttribute("user");
            System.out.println(user.getUsername());
        }
        boolean isLoggedIn = (session!=null && session.getAttribute("user")!=null);
        String loginURI = httpRequest.getContextPath()+"/admin/login";
        System.out.println(loginURI);
        System.out.println("----");
        System.out.println(httpRequest.getRequestURI());
        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean isLoginPage = httpRequest.getRequestURI().endsWith("login");
        if (isLoggedIn && (isLoginRequest || isLoginPage)){
            //the admin is already login ande he is trying login again
            //then forward to the admin homepage
            request.getRequestDispatcher("admin/home").forward(httpRequest,httpResponse);//go to home

        }else if (isLoggedIn || isLoginRequest){
            //System.out.println("chain.sout");
            Chain.doFilter(request,response);//go to next destination
            //httpResponse.sendRedirect(httpRequest.getContextPath()+"/admin/login");
        }else {

            System.out.println("6666666666666666");
            httpResponse.sendRedirect(httpRequest.getContextPath()+"/admin/login");//go to login
            //System.out.println((httpRequest.getContextPath() + "/admin/login"));
        }
    }

    @Override
    public void destroy() {

    }
}
