package com.HuangYufan.week6.ExerciseCode1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("search");
        if(name==null){
            response.sendRedirect("index.jsp");
        } else if (name.equals("baidu")) {
            response.sendRedirect("https://www.baidu.com");
        } else if (name.equals("bing")) {
            response.sendRedirect("https://www.cn.bing.com");
        } else if (name.equals("google")) {
            response.sendRedirect("https://www.google.com");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
