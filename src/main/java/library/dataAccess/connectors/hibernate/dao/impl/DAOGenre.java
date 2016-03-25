package library.dataAccess.connectors.hibernate.dao.impl;

import library.dataAccess.connectors.hibernate.entities.EntityBase;
import library.dataAccess.connectors.hibernate.entities.Genre;
import library.dataAccess.connectors.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DAOGenre extends BaseDAOImpl {
    public DAOGenre() {
        super(Genre.class);
    }
    @Override
    public List<EntityBase> searchEntityByName(EntityBase entity) {
        Genre genre = (Genre) entity;
        Session session = null;
        List<EntityBase> entities = new ArrayList<>();
        if (entity == null) return entities;
        try {
            session = HibernateUtil.getSession();
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    " e WHERE upper(e.title) LIKE upper(:title)")
                    .setParameter("title", "%" + genre.getTitle() + "%")
                    .list();
        } finally {HibernateUtil.close(session);}
        return entities;
    }


}
