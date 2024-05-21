package com.HuangYufan.controller;

import com.HuangYufan.DAO.userDao;
import com.HuangYufan.model.users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "UpdatePasswordServlet", value = "/UpdatePasswordServlet")
public class UpdatePasswordServlet extends HttpServlet {
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
        String newPassword=request.getParameter("password");
        HttpSession session = request.getSession(false);//如果你只想获取当前的会话而不创建新的会话，可以使用getSession(false)方法
        users user;
        user= (users) session.getAttribute("loginUser");
        userDao userDao=new userDao();
        try {
            int rowsAffected=userDao.updatePassword(con,user,newPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("login");
    }
}
