package com.HuangYufan.week4.demo;

import com.HuangYufan.model.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userDao {

    public List<users> selectALL() {

        Connection conn = DButil.getConnection();
        List<users> list = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement("select * from [userdb].[dbo].[Table_1]");
            rs = pstm.executeQuery();
            while (rs.next()) {
                //把数据从rs中取出来
                String username = rs.getString(1);
                String password = rs.getString(2);
                String email = rs.getString(3);
                String gender = rs.getString(4);
                String birthday = rs.getString(5);

                //把取出来的数据保存到对象中
                users user = new users();
                //把对象保存到集合中
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DButil.closeAll(pstm, conn, rs);
        }


        return list;
    }



    public int insert(users user) {

        String sql="insert into [userdb].[dbo].[Table_1] values(?,?,?,?,?)";
        Object[] obj={user.getUsername(),user.getPassword(),user.getEmail(),user.getGender(),user.getBirthday()};

        int update=DButil.exeUpdate(sql,obj);

        return update;
    }




}
