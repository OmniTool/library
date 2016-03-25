package library.dataAccess.connectors.hibernate.dao.impl;

import library.dataAccess.connectors.hibernate.entities.Book;
import library.dataAccess.connectors.hibernate.entities.EntityBase;
import library.dataAccess.connectors.hibernate.entities.Genre;
import library.dataAccess.connectors.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DAOBook extends BaseDAOImpl {
    public DAOBook() {
        super(Book.class);
    }
    @Override
    public List<EntityBase> searchEntityByName(EntityBase entity) {
        Book book = (Book) entity;
        Session session = null;
        List<EntityBase> entities = new ArrayList<>();
        if (entity == null) return entities;
        try {
            session = HibernateUtil.getSession();
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    " e WHERE upper(e.title) LIKE upper(:title)")
                    .setParameter("title", "%" + book.getTitle() + "%")
                    .list();
        } finally {HibernateUtil.close(session);}
        return entities;
    }
    public List<Book> searchBooksByGenre(Genre genre) {
        Session session = null;
        List<Book> entities = new ArrayList<>();
        if (genre == null) return entities;
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
