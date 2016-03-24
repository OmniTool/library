package library.dataAccess.connectors.hibernate.dao.impl;

import library.dataAccess.connectors.hibernate.entities.BookHiber;
import library.dataAccess.connectors.hibernate.entities.EntityBaseHiber;
import library.dataAccess.connectors.hibernate.entities.GenreHiber;
import library.dataAccess.connectors.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DAOBook extends BaseDAOImpl {
    public DAOBook() {
        super(BookHiber.class);
    }

    @Override
    public List<EntityBaseHiber> searchEntityByName(EntityBaseHiber entity) {
        BookHiber book = (BookHiber) entity;
        Session session = null;
        List<EntityBaseHiber> entities = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    " e WHERE upper(e.title) LIKE upper(:title)")
                    .setParameter("title", "%" + book.getTitle() + "%")
                    .list();
        } finally {HibernateUtil.close(session);}
        return entities;
    }

    public List<BookHiber> searchBooksByGenre(GenreHiber genre) {
        Session session = null;
        List<BookHiber> entities = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    " e WHERE e.genre.id = :genre_id")
                    .setParameter("genre_id", genre.getId())
                    .list();
        } finally {HibernateUtil.close(session);}
        return entities;
    }

}
