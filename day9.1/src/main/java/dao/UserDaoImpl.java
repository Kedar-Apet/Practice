package dao;

import static utils.HibernateUtils.getSf;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.User;

public class UserDaoImpl implements IUserDao {
	// no Data members,no constr,no cleanup

	@Override
	public String registerUser(User user) {
		String mesg = "User registration failed failed";
		// user:TRANSIENT [JAVA HEAP]
		// get sthe Session from SF
		Session session = getSf().openSession();
		Session session2 = getSf().openSession();
		System.out.println(session == session2);// false
		// begin atx
		Transaction tx = session.beginTransaction();// DB conn is pooled and wrapped in a Sesion obj
		System.out.println("session is open " + session.isOpen() + " connected to DB " + session.isConnected());// t t
		// n L1 cache is created here
		try {
			// org.hibernate.Session API: public Serializable save(Object transientObjRef)
			// throws HibernateException;
			Serializable userId = session.save(user);// user ref is added in cache:PERSISTENT
			tx.commit();// upon commit: Hibernate performs "auto dirty checking"
			// L1 cache with that of DB: DML will be fired (insert)
			mesg = "user registered succesfully with Id= " + userId;
			System.out.println("session is open " + session.isOpen() + " connected to DB " + session.isConnected());// t
																													// t
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			if (session != null)
				session.close();// pooled out DB connection returns to the pool
			// L1 cache is destroyed
		}
		System.out.println("session is open " + session.isOpen() + " connected to DB " + session.isConnected());// f f
		return mesg;
	}

	@Override
	public String registerUserWithGetCurrentSession(User user) {
		String mesg = "User registration failed failed";
		// user:TRANSIENT [JAVA HEAP]
		// get sthe Session from SF
		Session session = getSf().getCurrentSession();
		Session session2 = getSf().getCurrentSession();
		System.out.println(session == session2);// true
		// begin atx
		Transaction tx = session.beginTransaction();// DB conn is pooled and wrapped in a Sesion obj
		System.out.println("session is open " + session.isOpen() + " connected to DB " + session.isConnected());// t t
		// n L1 cache is created here
		try {
			// org.hibernate.Session API: public Serializable save(Object transientObjRef)
			// throws HibernateException;
			Serializable userId = session.save(user);// user ref is added in cache:PERSISTENT
			tx.commit();// upon commit: Hibernate performs "auto dirty checking"
			// L1 cache with that of DB: DML will be fired (insert)
			mesg = "user registered succesfully with Id= " + userId;
			System.out.println("session is open " + session.isOpen() + " connected to DB " + session.isConnected());// f
																													// f
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		System.out.println("session is open " + session.isOpen() + " connected to DB " + session.isConnected());// f f
		return mesg;
	}

	@Override
	public User getUserDetails(int userId) {
		User user = null;
		Session session = getSf().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			user = (User) session.get(User.class, userId);// int-->Integer-->Serializable
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return user;
	}

}
