package com.HuangYufan.controller;

import com.HuangYufan.DAO.userDao;
import com.HuangYufan.model.users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FindByUsername", value = "/FindByUsername")
public class FindByUsernameServlet extends HttpServlet {
    Connection con;
    @Override
    public void init() throws ServletException{
        con= (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/FindByUsername.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        userDao userDao=new userDao();
        List<users> users=new ArrayList<>();
        try {
            users=userDao.findByUsername(con,username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(!users.isEmpty()){
            request.setAttribute("users",users);
            request.getRequestDispatcher("WEB-INF/views/userList.jsp").forward(request,response);
        }else{
            request.setAttribute("ErrorMessage","This Username does not exist!!!");
            request.getRequestDispatcher("WEB-INF/views/FindByUsername.jsp").forward(request,response);
        }
    }
}
