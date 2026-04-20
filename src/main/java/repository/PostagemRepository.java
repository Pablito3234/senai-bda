package repository;

import models.Postagem;
import org.hibernate.SessionFactory;

public class PostagemRepository extends GenericRepository<Postagem>{
    public PostagemRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Postagem.class);
    }
}
