package com.HuangYufan.controller;

import com.HuangYufan.DAO.ProductDao;
import com.HuangYufan.model.Category;
import com.HuangYufan.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductDetailsServlet", value = "/productDetails")

public class ProductDetailsServlet extends HttpServlet {
    Connection con;
    @Override
    public void init() throws ServletException{
        con= (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> categoryList = Category.findAllCategory(con);
            request.setAttribute("categoryList", categoryList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //get product by id
        if (request.getParameter("id") != null) {
            int productId= Integer.parseInt(request.getParameter("id"));
            ProductDao productDao = new ProductDao();
            try {
                Product product = productDao.findById(productId, con);
                request.setAttribute("p",product);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        //forward
        String path="/WEB-INF/views/productDetails.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
