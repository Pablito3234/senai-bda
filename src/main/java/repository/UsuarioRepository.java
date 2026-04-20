package repository;

import models.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UsuarioRepository extends GenericRepository<Usuario>{
    public UsuarioRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Usuario.class);
    }

    public Usuario buscarPorEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "from Usuario u where u.email = :email",
                            Usuario.class
                    ).setParameter("email", email)
                    .uniqueResult();
        }
    }
}
