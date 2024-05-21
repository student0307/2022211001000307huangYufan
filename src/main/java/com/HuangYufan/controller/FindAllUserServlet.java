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

@WebServlet(name = "FindAllUser", value = "/FindAllUser")
public class FindAllUserServlet extends HttpServlet {
    Connection con;
    @Override
    public void init() throws ServletException{
        con= (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userDao userDao=new userDao();
        List<users> users=new ArrayList<>();
        try {
            users=userDao.findAllUser(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(!users.isEmpty()){
            request.setAttribute("users",users);
            request.getRequestDispatcher("WEB-INF/views/userList.jsp").forward(request,response);
        }else {
            request.setAttribute("ErrorMessage","There is no user exists!!!");
            request.getRequestDispatcher("WEB-INF/views/FindAllUser.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
