package library.dataAccess.hibernate.dao.impl;

import library.dataAccess.hibernate.entities.EntityBaseHiber;
import library.dataAccess.hibernate.entities.GenreHiber;
import library.dataAccess.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DAOGenre extends BaseDAOImpl {
    public DAOGenre() {
        super(GenreHiber.class);
    }

    @Override
    public List<EntityBaseHiber> searchEntityByName(EntityBaseHiber entity) {
        GenreHiber genre = (GenreHiber) entity;
        Session session = null;
        List<EntityBaseHiber> entities = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    "WHERE upper(title) LIKE upper(" + genre.getTitle() + ")")
                    .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return entities;
    }
}
