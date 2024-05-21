package com.HuangYufan.week4.demo;

import com.HuangYufan.model.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@WebServlet(
        urlPatterns = {"/userList"}
)
public class FindServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection conn = DButil.getConnection();
            Statement stmt=conn.createStatement();
            String sql="select * from [userdb].[dbo].[Table_1]";
            ResultSet rs=stmt.executeQuery(sql);
            List<users> list=new ArrayList<>();
            while (rs.next()){
                users user=new users();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setGender(rs.getString("gender"));
                user.setBirthday(rs.getString("birthday"));
                list.add(user);
            }
            request.setAttribute("list",list);
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("userList.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }
}
