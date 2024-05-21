package com.HuangYufan.controller;

import com.HuangYufan.DAO.ProductDao;
import com.HuangYufan.model.Category;
import com.HuangYufan.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UpdateProductServlet", value = "/admin/productEdit")
public class UpdateProductServlet extends HttpServlet {
    Connection con;
    @Override
    public void init() throws ServletException{
        con= (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //根据productId找出product传给jsp页面，显示原来数据
        int productId= Integer.parseInt(request.getParameter("productId"));
        ProductDao productDao=new ProductDao();
        Product product;
        try {
            product=productDao.findById(productId,con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //set all category into request
        try {
            List<Category> categoryList= Category.findAllCategory(con);
            request.setAttribute("categoryList",categoryList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("product",product);
        request.getRequestDispatcher("/WEB-INF/views/admin/updateProduct.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//get parameter

        String productName=request.getParameter("productName");
        System.out.println(productName);
        Double price =request.getParameter("price")!=null?Double.parseDouble(request.getParameter("price")):0.0;
        System.out.println(price);
        int categoryId=request.getParameter("categoryId")!=null?Integer.parseInt(request.getParameter("categoryId")):0;
        System.out.println(categoryId);
        String productDescription=request.getParameter("productDescription");
        System.out.println(productDescription);
        String productId= (request.getParameter("productId"));
        System.out.println(productId);
        int id1= Integer.parseInt(productId);
        //get picture
        InputStream inputStream=null;
        Part filePart=request.getPart("picture");
        if(filePart!=null){
            System.out.println(("file name:" + filePart.getName() + " size" + filePart.getSize() + " file type" + filePart.getContentType()));
            inputStream=filePart.getInputStream();
        }
        Product product=new Product();
        product.setProductName(productName);
        product.setPrice(price);
        product.setProductDescription(productDescription);
        product.setPicture(inputStream);
        product.setCategoryId(categoryId);
        product.setProductId(id1);

        ProductDao productDao=new ProductDao();
        int rows=0;
        try {
            rows=productDao.updateProduct(con,product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(rows>0){
            request.setAttribute("message","update successfully");
            response.sendRedirect("/admin/productList");
        }
    }
}
