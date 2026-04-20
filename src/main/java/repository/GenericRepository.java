package repository;

import jakarta.persistence.RollbackException;
import lombok.AllArgsConstructor;
import models.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@AllArgsConstructor
public abstract class GenericRepository<Entity> {
    protected SessionFactory sessionFactory;
    private Class<Entity> tClass;

    public void salvar(Entity entity) throws RollbackException {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        }
    }

    public Entity buscarPorId(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(tClass, id);
        }
    }

    public void remover(Entity entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
        }
    }
}
