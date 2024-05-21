package com.HuangYufan.controller;

import com.HuangYufan.DAO.IUserDao;
import com.HuangYufan.DAO.userDao;
import com.HuangYufan.model.users;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(
        urlPatterns ={"/register"},
        initParams = {
                @WebInitParam(name="driver",value = "com.microsoft.sqlserver.jdbc.SQLServerDriver"),
                @WebInitParam(name="url",value = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=userdb;encrypt=true;trustServerCertificate=true"),
                @WebInitParam(name="username",value = "sa"),
                @WebInitParam(name="password",value = "123456")
        },loadOnStartup = 1
)

public class RegisterServlet extends HttpServlet {
    Connection con=null;
    @Override
    public void init() throws ServletException{
        //String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        //String url ="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=userdb;encrypt=true;trustServerCertificate=true";
        //String username ="sa";
        //String password ="123456";

        /*ServletContext context=getServletContext();
        String driver=context.getInitParameter("driver");
        String url=context.getInitParameter("url");
        String username=context.getInitParameter("username");
        String password=context.getInitParameter("password");
        try {
            Class.forName(driver);
            con= DriverManager.getConnection(url, username, password);
            System.out.println("-->"+con);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
*/
        con= (Connection) getServletContext().getAttribute("con");

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/views/registerJDBC.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");//Username: <input type="text" id="username" required><br>
        String password=request.getParameter("password");// Password: <input type="password" id="password" name="password"required minlength="8"><br>
        String email=request.getParameter("email");//Email: <input type="email" id="email" name="email" required><br>
        String gender=request.getParameter("gender");//Gender:<input type="radio" name="sex" name="gender" />Male <input type="radio" name="sex" />Female<br/>
        String birthday=request.getParameter("birthday");//Birthdate (yyyy-mm-dd): <input type="text" id="birthdate" name="birthday" required><br>

        //PrintWriter writer=response.getWriter();
       // writer.println("<br>username: "+username);
        //writer.println("<br>password: "+password);
       // writer.println("<br>email: "+email);
       // writer.println("<br>gender: "+gender);
       //writer.println("<br>birthday: "+birthday);
       //writer.close();
        users user=new users(username,password,email,gender,birthday);
        PrintWriter writer=response.getWriter();
        userDao userdao=new userDao();
        int insert = 0;
        try {
            insert = userdao.saveUser(con,user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(insert>0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");

        }
        response.sendRedirect("login");
        //List<users> list = userdao.selectALL();
        // 通过遍历 list 来显示学员信息
        //for (users users : list) {
        //    writer.println(users); // 确保 Student 类有重写 toString 方法
        //}


    }
}


