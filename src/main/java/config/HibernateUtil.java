package config;

import org.hibernate.HibernateError;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() throws HibernateError{
        if (sessionFactory == null){
            buildSessionFactory();
        }
        return sessionFactory;
    }

    private static void buildSessionFactory() throws HibernateError {
        Configuration configuration = new Configuration();
        sessionFactory = configuration.configure().buildSessionFactory();
    }
}
