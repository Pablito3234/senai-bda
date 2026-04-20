package repository;

import models.Postagem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PostagemRepository extends GenericRepository<Postagem>{
    public PostagemRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Postagem.class);
    }

    public List<Postagem> listarPorUsuarioId(Long usuarioId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "select p from Postagem p where p.usuario.id = :usuarioId order by p.id",
                            Postagem.class
                    )
                    .setParameter("usuarioId", usuarioId)
                    .getResultList();
        }
    }
}
