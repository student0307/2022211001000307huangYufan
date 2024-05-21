package com.HuangYufan.controller;

import com.HuangYufan.DAO.userDao;
import com.HuangYufan.model.users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "FindById", value = "/FindById")

public class FindByIdServlet extends HttpServlet {
    Connection con;
    @Override
    public void init() throws ServletException{
        con= (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/FindById.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        userDao userDao = new userDao();
        users user;
        try {
            user = userDao.findById(con, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (user != null){
            request.setAttribute("id",user.getId());
            request.setAttribute("username",user.getUsername());
            request.setAttribute("password",user.getPassword());
            request.setAttribute("email",user.getEmail());
            request.setAttribute("gender",user.getGender());
            request.setAttribute("birthday",user.getBirthday());
            request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
        }else {
            request.setAttribute("ErrorMessage","This ID does not exist!!!");
            request.getRequestDispatcher("WEB-INF/views/FindByUsername.jsp").forward(request,response);
        }

    }
}
