package com.HuangYufan.DAO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import com.HuangYufan.model.Book;

public class BookDAO {
    Connection connection;
    String databaseURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=AppDB;encrypt=true;trustServerCertificate=true";
    String user = "sa";
    String password = "123456";
    String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public Book get(int id) throws SQLException, IOException, ClassNotFoundException {
        Book book = null;

        String sql = "SELECT * FROM contacts WHERE contact_id = ?";

        try  {
            Class.forName(driver);
            connection= DriverManager.getConnection(databaseURL, user, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                book = new Book();
                String title = result.getString("first_name");
                String author = result.getString("last_name");
                Blob blob = result.getBlob("photo");

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);


                inputStream.close();
                outputStream.close();

                book.setTitle(title);
                book.setAuthor(author);
                book.setBase64Image(base64Image);
            }

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return book;
    }
}