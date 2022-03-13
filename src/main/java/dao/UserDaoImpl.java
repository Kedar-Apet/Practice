package dao;

import java.sql.*;
import static utils.DBUtils.fetchDBCOnnection;
import java.sql.SQLException;

import pojo.User;

public class UserDaoImpl implements IUserDao {
	private Connection cn;
	private PreparedStatement pst1;

	public UserDaoImpl() throws SQLException {
		cn = fetchDBCOnnection();
		pst1 = cn.prepareStatement("select * from users where email=? and password=?");
		System.out.println("User dao created");
	}

	@Override
	public User validateUser(String email, String password) throws SQLException {
		pst1.setString(1, email);
		pst1.setString(2, password);
		try(ResultSet rst=pst1.executeQuery()){
			if(rst.next()) {
				return new User(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getDouble(5),rst.getDate(6),rst.getString(7));
			}
			
		}
		return null;
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		System.out.println("user dao cleaned up");
	}

}
