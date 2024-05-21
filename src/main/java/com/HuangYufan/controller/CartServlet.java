package com.HuangYufan.controller;

import com.HuangYufan.DAO.ProductDao;
import com.HuangYufan.model.Item;
import com.HuangYufan.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    Connection con;
    @Override
    public void init() throws ServletException{
        con= (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        if(session!=null&&session.getAttribute("user")!=null){
            //session already exits
            if(request.getParameter("action")==null){
                displayCart(request,response);
            } else if (request.getParameter("action").equals("add")) {
                try {
                    buy(request,response);
                }catch (SQLException e){
                    e.printStackTrace();
                }
            } else if (request.getParameter("action").equals("remove")) {
                remove(request,response);
            }
        }else {
            response.sendRedirect("login");
        }
    }
    private void buy (HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException,
            SQLException{
        HttpSession session=request.getSession();
        int id=request.getParameter("productId")!=null?Integer.parseInt(request.getParameter("productId")):1;
        int quantityParam=request.getParameter("quantity")!=null?Integer.parseInt(request.getParameter("quantity")):1;
        ProductDao dao=new ProductDao();
        if(session.getAttribute("cart")==null){//new cart
            List<Item> cart=new ArrayList<>();
            Product p=dao.findById(id,con);
            cart.add(new Item(p,quantityParam));
            session.setAttribute("cart",cart);
        }else {//existing cart
            List<Item> cart= (List<Item>) session.getAttribute("cart");
            int index=isExisting(id,cart);
            if(index==-1){
                cart.add(new Item(dao.findById(id,con),1));
            }else{
                int quantity = cart.get(index).getQuantity()+1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart",cart);
        }
        response.sendRedirect(request.getContextPath()+"/cart");
    }

    private void remove (HttpServletRequest request,HttpServletResponse response)throws IOException{
        HttpSession session=request.getSession();
        //unchecked
        List<Item> cart= (List<Item>) session.getAttribute("cart");
        int id=0;
        if(request.getParameter("productId")!=null){
            id= Integer.parseInt(request.getParameter("productId"));
        }
        int index=isExisting(id,cart);
        cart.remove(index);
        session.setAttribute("cart",cart);
        response.sendRedirect(request.getContextPath()+"/cart");

    }

    private int isExisting(int id,List<Item> cart){
        for (int i = 0; i < cart.size(); i++) {
            if(cart.get(i).getProduct().getProductId()==id){
                return i;
            }
        }
        return -1;
    }

    private void displayCart(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        request.setAttribute("message","Your Cart");
        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
