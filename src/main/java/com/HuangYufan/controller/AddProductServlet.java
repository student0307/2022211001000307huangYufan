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
import java.util.logging.Logger;

@WebServlet(name = "AddProductServlet", value = "/admin/addProduct")
@MultipartConfig(maxFileSize = 16177215)
public class AddProductServlet extends HttpServlet {
    //private Connection con=null;
    Connection con;
    @Override
    public void init() throws ServletException{
        con= (Connection) getServletContext().getAttribute("con");
    }
    private static final Logger Log=Logger.getLogger(String.valueOf(AddProductServlet.class));
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category();
        List<Category> categoryList;
        try {
            categoryList = Category.findAllCategory(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("categoryList", categoryList);
        String path="/WEB-INF/views/admin/addProduct.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get parameter
        String productName=request.getParameter("productName");
        Double price =request.getParameter("price")!=null?Double.parseDouble(request.getParameter("price")):0.0;
        int categoryId=request.getParameter("categoryId")!=null?Integer.parseInt(request.getParameter("categoryId")):0;
        String productDescription=request.getParameter("productDescription");

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

        ProductDao dao=new ProductDao();
        int i=0;
        try {
            i=dao.insertProduct(con,inputStream,product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(i>0){
            response.sendRedirect("productList");
        }
    }
}
