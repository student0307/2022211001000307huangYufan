package com.HuangYufan.DAO;

import com.HuangYufan.model.users;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface IUserDao {
    public int saveUser(Connection con, users user) throws SQLException;
    public int deleteUser(Connection con, users user) throws SQLException;



    int updatePassword(Connection con, users user, String newPassword) throws SQLException;

    public int updateUser(Connection con, users user) throws SQLException;

    public users findById(Connection con, Integer id) throws SQLException;
    public users findByUsernamePassword(Connection con, String username,String password) throws SQLException;
    public List<users> findByUsername(Connection con, String username) throws SQLException;
    public List<users> findByPassword(Connection con, String password) throws SQLException;
    public List<users> findByEmail(Connection con,String email) throws SQLException;
    public List<users> findByGender(Connection con,String gender) throws SQLException;
    public List<users> findByBirthdate(Connection con, Date birthDate) throws SQLException;
    public List<users> findAllUser(Connection con) throws SQLException;


}
