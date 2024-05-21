package com.HuangYufan.week7.demo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
    Connection con=null;
    @Override
    public void init() throws ServletException{
        con= (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       // String password=request.getParameter("password");
       // String username=request.getParameter("username");
        String password=String.valueOf(request.getParameter("password"));
        String username=String.valueOf(request.getParameter("username"));
        String sql="UPDATE [userdb].[dbo].[Table_1] SET password=? WHERE username=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,password);
            ps.setString(2,username);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("login");
    }
}
