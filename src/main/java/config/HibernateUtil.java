package config;

import models.Postagem;
import models.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            buildSessionFactory();
        }
        return sessionFactory;
    }

    private static void buildSessionFactory() {
        Configuration configuration = new Configuration();
//        sessionFactory = configuration.configure().buildSessionFactory();
        configuration.configure();
        configuration.addAnnotatedClass(Usuario.class);
        configuration.addAnnotatedClass(Postagem.class);
        sessionFactory = configuration.buildSessionFactory();
    }
}
