package tester;

import static utils.HibernateUtils.getSf;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;
import pojos.User;
import pojos.UserRole;

public class RegisterUserWithGetCurrenmtSession {
public static void main(String[] args) {
	try(SessionFactory sf=getSf(); Scanner sc=new Scanner(System.in)){
		/*
		 * String name, String email, String password, UserRole role, String confirmPassword, double regAmount,
			LocalDate reqDate
		 */
		System.out.println("Enter user details name,email,password,userole,confirmpassword,regAmount,regDate(yr-mon-day)");
		//create transient pojo exists in heap
		User user=new User(sc.next(),sc.next(),sc.next(),UserRole.valueOf(sc.next().toUpperCase()),sc.next(),sc.nextDouble(),LocalDate.parse(sc.next()));
		UserDaoImpl userDao=new UserDaoImpl();
		String mesg = userDao.registerUserWithGetCurrentSession(user);
		System.out.println(mesg);
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}
