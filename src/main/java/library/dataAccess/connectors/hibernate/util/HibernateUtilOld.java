package library.dataAccess.connectors.hibernate.util;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class HibernateUtilOld {
    private static SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }
    public static void close(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}