package library.dataAccess.connectors.hibernate.dao.impl;

import library.dataAccess.connectors.hibernate.entities.EntityBaseHiber;
import library.dataAccess.connectors.hibernate.entities.GenreHiber;
import library.dataAccess.connectors.hibernate.util.HibernateUtil;
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
            session = HibernateUtil.getSession();
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    " e WHERE upper(e.title) LIKE upper(:title)")
                    .setParameter("title", "%" + genre.getTitle() + "%")
                    .list();
//            SQLQuery query = session.createSQLQuery("SELECT * FROM genres WHERE upper(title) LIKE upper(:title)");
//            entities = query
//                    .setString( "title", genre.getTitle())
//                    .list();
        } finally {HibernateUtil.close(session);}
        return entities;
    }


}
