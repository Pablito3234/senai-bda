package repository;

import lombok.AllArgsConstructor;
import models.Usuario;
import org.hibernate.SessionFactory;

public class UsuarioRepository extends GenericRepository<Usuario>{
    public UsuarioRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Usuario.class);
    }
}
