package utils;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
private static SessionFactory sf;
//how to ensure creation of singleton instance of session factory :EAGER singletong pattern
static {
	System.out.println("in static init block");
	sf=new Configuration().configure().buildSessionFactory();
}
public static SessionFactory getSf() {
	return sf;
}

}
