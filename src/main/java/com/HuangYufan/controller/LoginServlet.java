package com.HuangYufan.controller;

import com.HuangYufan.DAO.userDao;
import com.HuangYufan.model.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static java.lang.System.out;


@WebServlet(name = "LoginServlet", value = {"/login"})
public class LoginServlet extends HttpServlet{
    Connection con=null;
    @Override
    public void init() throws ServletException{
        con= (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //HttpSession session = request.getSession();
        // user = (users) session.getAttribute("loginUser");
        //if (user != null ) {
        //    request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request, response);
        //} else {
        //    request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
        //}
        HttpSession session = request.getSession(false);

        Cookie[] cookies = request.getCookies();
        if (cookies != null&& session!=null) {
            //session = request.getSession(false);
            users user = (users) session.getAttribute("user");
            if (user != null) {
                String Id = String.valueOf(user.getId());
                for (Cookie c : cookies) {
                    if ("rememberMe".equals(c.getName()) && Id.equals(c.getValue())) {
                        // 根据cookie中的信息自动登录
                        if (session.getAttribute("user") != null) {
                            // 用户已经登录，继续使用会话
                            request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request, response);
                        }

                    }
                }
            }
        }
            //  session.invalidate();

        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
            //System.out.println(session);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String username = request.getParameter("username");//Username: <input type="text" id="username" required><br>
        String password = request.getParameter("password");
        boolean rememberMe = "true".equals(request.getParameter("rememberMe"));
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

            //新建3个Cookies用于RememberMe
            if("1".equals (request.getParameter("rememberMe2"))){
                //create 3 Cookies
                Cookie usernameCookie=new Cookie("cUsername",user.getUsername());
                Cookie passwordCookie=new Cookie("cPassword",user.getPassword());
                Cookie rememberMeCookie=new Cookie("rememberMeVal",request.getParameter("rememberMe2"));
                //set age
                usernameCookie.setMaxAge(5);
                passwordCookie.setMaxAge(5);
                rememberMeCookie.setMaxAge(5);
                //add Cookies into response
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
                response.addCookie(rememberMeCookie);
            }


            //保持登陆状态
            if(rememberMe){
                String Id= String.valueOf(user.getId());
                Cookie rememberMeCookie = new Cookie("rememberMe",Id);
                rememberMeCookie.setMaxAge(60 * 60 * 24 * 7); // 设置为7天
                //rememberMeCookie.setPath("/"); // 设置cookie的路径，这里设置为根路径
                response.addCookie(rememberMeCookie);
            }
            request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
        }else {
            request.setAttribute("message","Username or Password Error!");
            request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
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
