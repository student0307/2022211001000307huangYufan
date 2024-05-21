package com.HuangYufan.week4.demo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(
        urlPatterns = {"/cofig"},
        initParams = {
                @WebInitParam(name="name1",value ="HuangYufan"),
                @WebInitParam(name="studentID1",value ="2022211001000307")
        },loadOnStartup = 2

)

public class ConfigDemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletConfig config=getServletConfig();
        String name=config.getInitParameter("name");
        String studentID=config.getInitParameter("studentID");
        String name1=config.getInitParameter("name1");
        String studentID1=config.getInitParameter("studentID1");

        PrintWriter writer=response.getWriter();
        writer.println("Task1-Get init parameters from web.xml");
        writer.println("Name: "+name);
        writer.println("StudentID: "+studentID);
        writer.println("Task2-Get init parameters from @WebServer");
        writer.println("Name: "+name1);
        writer.println("StudentID: "+studentID1);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }
}
