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

@WebServlet(name = "shopServlet", value = "/shop")
public class shopServlet extends HttpServlet {
    Connection con;
    @Override
    public void init() throws ServletException{
        con= (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //set all category into request
        try {
            List<Category> categoryList= Category.findAllCategory(con);
            request.setAttribute("categoryList",categoryList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //set all into request
        ProductDao productDao =new ProductDao();
        try {
            if(request.getParameter("categoryId")==null) {
                //show all product
                List<Product> productList = productDao.selectAllProduct(con);
                request.setAttribute("productList", productList);
            }else {
                //show only one type product
                int catId= Integer.parseInt(request.getParameter("categoryId"));
                List<Product> productList=productDao.findByCategoryId(catId,con);
                request.setAttribute("productList", productList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //forward
        String path="/WEB-INF/views/shop.jsp";
        request.getRequestDispatcher(path).forward(request,response);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
