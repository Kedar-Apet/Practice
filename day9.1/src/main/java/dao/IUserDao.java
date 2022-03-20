package dao;

import pojos.User;

public interface IUserDao {
String registerUser(User user);//open session
String registerUserWithGetCurrentSession(User user);//getCurrentSession
User getUserDetails(int userId);
}
