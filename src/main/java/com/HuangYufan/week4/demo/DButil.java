package com.HuangYufan.week4.demo;

import java.sql.*;

public class DButil {
    public static Connection getConnection(){
        Connection conn=null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url ="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=DBtest;encrypt=true;trustServerCertificate=true";
            String username ="sa";
            String password ="123456";
            conn = DriverManager.getConnection(url, username, password);
        }catch (ClassNotFoundException e){
            e.printStackTrace();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    public static int exeUpdate(String sql,Object[] obj){
        Connection conn=getConnection();
        PreparedStatement pstm=null;
        int update=0;
        try{
            pstm=conn.prepareStatement(sql);
            for(int i=0;i<obj.length;i++){
                pstm.setObject(i+1,obj[i]);
            }
            update = pstm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeAll(pstm,conn,null);
        }
        return update;
    }
    public static void closeAll(PreparedStatement pstm, Connection conn, ResultSet rs){
        try {
            if(rs!=null){
                rs.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try {
            if (pstm!=null){
                pstm.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(conn!=null){
                conn.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
