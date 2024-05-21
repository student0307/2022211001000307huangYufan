package com.HuangYufan.week6.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
// tell tomcat this class is listener
@WebListener

public class JDBCServletContextListener implements ServletContextListener {
    Connection con=null;
    @Override
    public void contextInitialized(ServletContextEvent sce){
        System.out.println("i am in contextInitialized");
        ServletContext context=sce.getServletContext();
        String driver=context.getInitParameter("driver");
        String url=context.getInitParameter("url");
        String username=context.getInitParameter("username");
        String password=context.getInitParameter("password");
        try {
            Class.forName(driver);
            con= DriverManager.getConnection(url, username, password);
            System.out.println("i am in contextInitialized()-->"+con);
            context.setAttribute("con",con);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void contextDestroyed(ServletContextEvent sce){
        System.out.println("i am in contextDestroyed");
        sce.getServletContext().removeAttribute("con");
    }
}
