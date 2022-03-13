package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pojo.User;

class TestUserDaoImpl {
	private UserDaoImpl	 userDao;
	@BeforeEach
	void setUp() throws Exception {
		 userDao=new UserDaoImpl();
	}

	@Test
	void testValidateUser() throws SQLException {
		User validateUser = userDao.validateUser("kir@gmail.com", "123");
		
		assertEquals("Kiran",validateUser.getName() );
		userDao.cleanUp();
	}

}
