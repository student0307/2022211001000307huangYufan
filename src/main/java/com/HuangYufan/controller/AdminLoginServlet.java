package com.HuangYufan.controller;

import com.HuangYufan.DAO.userDao;
import com.HuangYufan.model.users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "AdminLoginServlet", value = "/admin/login")
public class AdminLoginServlet extends HttpServlet {
    Connection con=null;
    @Override
    public void init() throws ServletException{
        con= (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getContextPath());
        request.getRequestDispatcher("../WEB-INF/views/admin/adminLogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String username = request.getParameter("username");//Username: <input type="text" id="username" required><br>
        String password = request.getParameter("password");
        userDao userdao = new userDao();
        users user;
        try {
            user=userdao.findByUsernamePassword(con,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (user!=null){
            //request.setAttribute("user",user);

            HttpSession session=request.getSession();//在Servlet中，你可以通过HttpServletRequest对象的getSession方法获取
            // HttpSession对象。如果当前请求中没有会话，调用此方法将创建一个新的会话。
            session.setMaxInactiveInterval(20*60);//设置session持续20分钟
            //session.invalidate();
            session.setAttribute("user",user);

            request.getRequestDispatcher("home").forward(request,response);
        }else {
            System.out.println("test2222");
            request.setAttribute("message","Username or Password Error!");
            request.getRequestDispatcher("WEB-INF/views/admin/adminLogin.jsp").forward(request,response);
        }




      /*  String username = request.getParameter("username");//Username: <input type="text" id="username" required><br>
        String password = request.getParameter("password");
        Statement statement = null;
        ResultSet rs;
        PrintWriter writer=response.getWriter();
        try {
            statement = con.createStatement();

            String sql = "SELECT username,password,email,gender,birthday FROM [userdb].[dbo].[Table_1] WHERE username='" + username + "' AND password='" + password + "'";

            rs = statement.executeQuery(sql);

            if (rs.next()) {

                request.setAttribute("username",rs.getString("username"));
                request.setAttribute("password",rs.getString("password"));
                request.setAttribute("email",rs.getString("email"));
                request.setAttribute("gender",rs.getString("gender"));
                request.setAttribute("birthday",rs.getString("birthday"));
                request.getRequestDispatcher("userInfo.jsp").forward(request,response);

            } else {

                request.setAttribute("ErrorMessage","Username or Password Error!");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }

            //rs.close();
            //statement.close();
            //con.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
*/
    }

}
