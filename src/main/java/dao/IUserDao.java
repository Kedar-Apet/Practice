package dao;

import java.sql.SQLException;

import pojo.User;

public interface IUserDao {
User validateUser(String email,String password) throws SQLException;
}
