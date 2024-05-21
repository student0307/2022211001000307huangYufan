package com.HuangYufan.controller;

import com.HuangYufan.DAO.userDao;
import com.HuangYufan.model.users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "update", value = "/update")
public class UpdateServlet extends HttpServlet {
    //每个servlet前复制，进行数据库的链接
    Connection con;
    @Override
    public void init() throws ServletException{
        con= (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/updateUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String gender=request.getParameter("gender");
        String birthday=request.getParameter("birthday");
        users user=new users(id,username,password,email,gender,birthday);
        userDao userDao=new userDao();
        int rowsAffected;
        try {
            rowsAffected=userDao.updateUser(con,user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(rowsAffected>0){
            request.setAttribute("success2","Update Successfully");
            request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
        }else{
            request.setAttribute("fail","Update Failed");
            request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
        }
    }
}
