package com.HuangYufan.DAO;

import com.HuangYufan.model.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class userDao implements IUserDao{
    @Override
    public int saveUser(Connection con, users user) throws SQLException {
        //insert
        String sql="insert into [userdb].[dbo].[Table_1] values(?,?,?,?,?)";
        //Object[] obj={user.getUsername(),user.getPassword(),user.getEmail(),user.getGender(),user.getBirthday()};
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUsername());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getEmail());
        pstmt.setString(4, user.getGender());
        pstmt.setString(5, user.getBirthday());
        return pstmt.executeUpdate();
        
    }

    @Override
    public int deleteUser(Connection con, users user) throws SQLException {
        String sql="DELETE FROM Table_1 WHERE id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,user.getId());
        int rowsAffected=pstmt.executeUpdate();
        return rowsAffected;
    }//注销已登录的账户

    @Override
    public int updatePassword(Connection con, users user,String newPassword) throws SQLException {
        String sql = "UPDATE [userdb].[dbo].[Table_1] SET password=? WHERE id=?";
        int rowsAffected;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setInt(2, user.getId());
            rowsAffected = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rowsAffected;
    }

    @Override
    public int updateUser(Connection con, users user) throws SQLException {
        String sql="UPDATE [Table_1] SET username=?,password=?,email=?,gender=?,birthday=? WHERE id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUsername());
        pstmt.setString(2,user.getPassword());
        pstmt.setString(3,user.getEmail());
        pstmt.setString(4,user.getGender());
        pstmt.setString(5,user.getBirthday());
        pstmt.setString(6, String.valueOf(user.getId()));
        int rowAffected=pstmt.executeUpdate();

        return rowAffected;
    }

    @Override
    public users findById(Connection con, Integer id) throws SQLException {
        String sql="select * from [userdb].[dbo].[Table_1] where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet rs=pstmt.executeQuery();
        users user=new users();
        if (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthday(rs.getString("birthday"));
            return user;
        }else{
            return null;
        }
    }

    @Override
    public users findByUsernamePassword(Connection con, String username, String password) throws SQLException {
        String sql="select * from [userdb].[dbo].[Table_1] where username=? and password=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,username);
        pstmt.setString(2,password);
        ResultSet rs=pstmt.executeQuery();
        users user=new users();
        //user=null;
        if (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthday(rs.getString("birthday"));
        }else {
            return null;
        }
        return user;
    }

    @Override
    public List<users> findByUsername(Connection con, String username) throws SQLException {
        String sql="select * from [userdb].[dbo].[Table_1] where username=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,username);
        ResultSet rs=pstmt.executeQuery();
        List<users> users =new ArrayList<>();
        while(rs.next()){
            users user=new users();
            user.setId(rs.getInt(1));
            user.setUsername(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setGender(rs.getString(5));
            user.setBirthday(rs.getString(6));
            users.add(user);
        }
        rs.close();
        pstmt.close();
        return users;
    }

    @Override
    public List<users> findByPassword(Connection con, String password) throws SQLException {
        return null;
    }

    @Override
    public List<users> findByEmail(Connection con, String email) throws SQLException {
        return null;
    }

    @Override
    public List<users> findByGender(Connection con, String gender) throws SQLException {
        return null;
    }

    @Override
    public List<users> findByBirthdate(Connection con, Date birthDate) throws SQLException {
        return null;
    }

    @Override
    public List<users> findAllUser(Connection con) throws SQLException {
        String sql="select * from Table_1";
        PreparedStatement pstmt=con.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        List<users> users=new ArrayList<>();
        while (rs.next()){
            users user=new users();
            user.setId(rs.getInt(1));
            user.setUsername(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setGender(rs.getString(5));
            user.setBirthday(rs.getString(6));
            users.add(user);
        }
        rs.close();
        pstmt.close();
        return users;
    }
}
