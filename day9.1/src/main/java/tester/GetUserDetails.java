package tester;

import static utils.HibernateUtils.getSf;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;
import pojos.User;
import pojos.UserRole;

public class GetUserDetails {
	public static void main(String[] args) {
		try (SessionFactory sf = getSf(); Scanner sc = new Scanner(System.in)) {
			/*
			 * String name, String email, String password, UserRole role, String
			 * confirmPassword, double regAmount, LocalDate reqDate
			 */
			System.out.println(
					"Enter user id");
			// create transient pojo exists in heap

			UserDaoImpl userDao = new UserDaoImpl();
			User user = userDao.getUserDetails(sc.nextInt());
			if (user != null)
				System.out.println(user);
			else
				System.out.println("user does not exist");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
