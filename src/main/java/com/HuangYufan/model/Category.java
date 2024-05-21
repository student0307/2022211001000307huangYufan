package com.HuangYufan.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private int categoryId;
    private String categoryName;
    private String description;
    private boolean active;



    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Category() {
    }

    public Category(int categoryId, String categoryName, String description, boolean active) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.active = active;
    }

    public static List<Category> findAllCategory(Connection con) throws SQLException{
        String sql="select * from Category";
        List<Category> list=new ArrayList<>();
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            Category category=new Category();
            category.setCategoryId(rs.getInt(1));
            category.setCategoryName(rs.getString(2));
            category.setDescription(rs.getString(3));
            category.setActive(rs.getBoolean(4));
            list.add(category);
        }
        return list;
    }

    public static String findByCategoryId(Connection con,int categoryId) throws SQLException {
        String categoryName=null;
        String sql="select CategoryName from Category where CategoryId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,categoryId);
        ResultSet rs=pstmt.executeQuery();
        while (rs.next()){
            categoryName=rs.getString("CategoryName");
        }
        return categoryName;
    }
}
