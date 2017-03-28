package ua.com.foxminded.serviceacc.model.dao.implementation;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			System.err.println("Initial SessionFactory creation failed." + e);

		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
