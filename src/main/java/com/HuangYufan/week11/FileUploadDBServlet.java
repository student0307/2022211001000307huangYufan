package com.HuangYufan.week11;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "FileUploadDBServlet", value = "/uploadServlet")
@MultipartConfig(maxFileSize =16177215)//upload file's size up to 16MB
public class FileUploadDBServlet extends HttpServlet {
                    Connection con;
                    String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
                    String url= "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=AppDB;encrypt=true;trustServerCertificate=true";
                    String username= "sa";
                    String password="123456";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        InputStream inputStream=null;//input stream of the upload file
        Part filePart=request.getPart("photo");
        if(filePart!=null){
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        String message="null";

        String sql="insert into contacts(first_name,last_name,photo) values(?,?,?)";
        try {
            Class.forName(driver);
            con= DriverManager.getConnection(url, username, password);
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setString(1,firstName);
            pstmt.setString(2,lastName);
            if(inputStream!=null){
                // fetches input stream of the upload file for the blob column
                pstmt.setBlob(3, inputStream);
            }
            int row=pstmt.executeUpdate();
            if(row>0){
                message="File uploaded and saved into database!!";
            }
        } catch (SQLException e) {
            message="ERROR!!"+e.getMessage();
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if(con!=null){
                // closes the database connection
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                // sets the message in request scope
                request.setAttribute("Message", message);

                // forwards to the message page
                getServletContext().getRequestDispatcher("/Message.jsp").forward(request, response);
            }
        }


    }
}
