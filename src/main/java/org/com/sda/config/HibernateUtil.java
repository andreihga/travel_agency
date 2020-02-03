package org.com.sda.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

//    what's the difference between "private static Session session = sessionFactory.openSession();" and the below getSession

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed " + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }
}
