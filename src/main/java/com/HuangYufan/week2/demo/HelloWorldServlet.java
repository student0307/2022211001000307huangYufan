package com.HuangYufan.week2.demo;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;

public class HelloWorldServlet extends HttpServlet {

    Date now = new Date();


    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
        PrintWriter writer=response.getWriter();
        writer.println("Name: Huang Yufan");
        writer.println("ID:2022211001000307");
        writer.println("Time:"+now);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response){

    }

}

