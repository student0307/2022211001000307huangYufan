package com.HuangYufan.controller;

import com.HuangYufan.DAO.ProductDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "ProductDeleteServlet", value = "/admin/productDelete")
public class ProductDeleteServlet extends HttpServlet {
    Connection con;
    @Override
    public void init() throws ServletException{
        con= (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id=0;
        int i;
        if((request.getParameter("productId")!=null)){
            ProductDao productDao=new ProductDao();
            id= Integer.valueOf(request.getParameter("productId"));
            try {
                productDao.delete(id,con);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("message","DELETE SUCCESSFULLY!!!");
            response.sendRedirect("productList");
        }
//        else{
//            request.setAttribute("message","ID NOT EXISTS!!!");
//            response.sendRedirect("/WEB-INF/views/admin/productList.jsp");
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
