package com.HuangYufan.DAO;

import com.HuangYufan.model.Product;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IProductDao {
    int insertProduct(Connection con, InputStream picture, Product product) throws SQLException;
    List<Product> selectAllProduct(Connection con, Product product) throws SQLException;

    List<Product> selectAllProduct(Connection con) throws SQLException;

    int updateProduct(Connection con, Product product) throws SQLException;

    public int delete(Integer productId, Connection con) throws SQLException;
    /**
     *	Update a product by productId
     * @return int
     */
    public Product findById(Integer productId, Connection con) throws SQLException;
    /**
     * Select all product by categoryId
     * @param categoryId
     * @return List of product
     */
    public List<Product> findByCategoryId(int categoryId, Connection con) throws SQLException;
    /**
     * Select product by price between minprice and maxPrice
     * @return List of product
     */
    public List<Product> findByPrice(double minPrice, double maxPrice,Connection con) throws SQLException;
    /**
     * Find all products
     * @return List of product
     */
    public List<Product> findByProductName(String productName, Connection con) throws SQLException;
    /**
     * Select all product by name like
     * @return List of product
     */
    public List<Product> getPicture(Integer productId, Connection con) throws SQLException;
}
