package com.HuangYufan.DAO;

import com.HuangYufan.model.Product;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao{
    @Override
    public int insertProduct(Connection con, InputStream picture, Product product) throws SQLException {
        int n=0;
        String sql="insert into Product(ProductName,ProductDescription,picture,price,CategoryId) values(?,?,?,?,?)";
        PreparedStatement pstmt =con.prepareStatement(sql);
        pstmt.setString(1,product.getProductName());
        pstmt.setString(2,product.getProductDescription());
        if (picture!=null) {
            pstmt.setBinaryStream(3,picture);
        }
        pstmt.setDouble(4,product.getPrice());
        pstmt.setInt(5,product.getCategoryId());
        n=pstmt.executeUpdate();
        return (n>0)?n:0;
    }

    @Override
    public List<Product> selectAllProduct(Connection con, Product product) throws SQLException {
        return null;
    }

    @Override
    public List<Product> selectAllProduct(Connection con) throws SQLException {
        String sql="select * from Product";
        PreparedStatement pstmt=con.prepareStatement(sql);
        ResultSet rs= pstmt.executeQuery();
        List<Product> products =new ArrayList<>();
        while(rs.next()){
            Product product1=new Product();
            product1.setProductId(rs.getInt(1));
            product1.setProductName(rs.getString(2));
            product1.setProductDescription(rs.getString(3));
            product1.setPicture(rs.getBinaryStream(4));
            product1.setPrice(rs.getDouble(5));
            product1.setCategoryId(rs.getInt(6));
            products.add(product1);
        }

        return products;
    }

    @Override
    public int updateProduct(Connection con, Product product) throws SQLException {
        String sql="update Product set ProductName=?, ProductDescription=?, picture=?, price=?, CategoryId=? where ProductId=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,product.getProductName());
        ps.setString(2,product.getProductDescription());
        ps.setBinaryStream(3,product.getPicture());
        ps.setDouble(4,product.getPrice());
        ps.setInt(5,product.getCategoryId());
        ps.setInt(6,product.getProductId());
        return ps.executeUpdate();
    }

    @Override
    public int delete(Integer productId, Connection con) throws SQLException {
        String sql="delete from Product where ProductId=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setInt(1,productId);
        int rowsAffected=ps.executeUpdate();
        return rowsAffected;
    }//删除产品

    @Override
    public Product findById(Integer productId, Connection con) throws SQLException {
        String sql="select * from Product where ProductId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,productId);
        ResultSet rs= pstmt.executeQuery();
        Product product1 = new Product();
        while(rs.next()){
            product1.setProductId(rs.getInt(1));
            product1.setProductName(rs.getString(2));
            product1.setProductDescription(rs.getString(3));
            product1.setPicture(rs.getBinaryStream(4));
            product1.setPrice(rs.getDouble(5));
            product1.setCategoryId(rs.getInt(6));
            
        }

        return product1;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId, Connection con) throws SQLException {
        String sql="select * from Product where CategoryId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,categoryId);
        ResultSet rs= pstmt.executeQuery();
        List<Product> products =new ArrayList<>();
        while(rs.next()){
            Product product1=new Product();
            product1.setProductId(rs.getInt(1));
            product1.setProductName(rs.getString(2));
            product1.setProductDescription(rs.getString(3));
            product1.setPicture(rs.getBinaryStream(4));
            product1.setPrice(rs.getDouble(5));
            product1.setCategoryId(rs.getInt(6));
            products.add(product1);
        }

        return products;
    }

    @Override
    public List<Product> findByPrice(double minPrice, double maxPrice, Connection con) throws SQLException {
        return null;
    }

    @Override
    public List<Product> findByProductName(String productName, Connection con) throws SQLException {
        return null;
    }

    @Override
    public List<Product> getPicture(Integer productId, Connection con) throws SQLException {
        return null;
    }

    public byte[] getPictureById(Integer productId,Connection con) throws SQLException {
        byte[] imgByte=null;
        String sql ="select picture from product where ProductId=?";
        PreparedStatement pt=con.prepareStatement(sql);
        pt.setInt(1,productId);
        ResultSet rs=pt.executeQuery();
        while (rs.next()){
            Blob blob = rs.getBlob("picture");
            imgByte=blob.getBytes(1,(int)blob.length());
        }
        return imgByte;
    }

}
