package com.HuangYufan.week6.demo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "redirectServlet", value = "/redirect")
public class Redirect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("before redirect ");
        //response.sendRedirect("index.jsp");
        //http://localhost:8080/huangYufan2022211001000307_war_exploded/index.jsp  --right
        System.out.println("after redirect ");
        //sendRedirect("/index.jsp");
        //http://localhost:8080/index.jsp  --wrong!!!
        //response.sendRedirect("/huangYufan2022211001000307_war_exploded/index.jsp");

        response.sendRedirect("https://www.baidu.com");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
