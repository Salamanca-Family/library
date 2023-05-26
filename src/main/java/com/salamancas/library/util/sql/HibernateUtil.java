package com.salamancas.library.util.sql;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory = null;

	public static void init() {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			System.out.println("Session factory not initialized");
			return null;
		}
		return sessionFactory;
	}

}
