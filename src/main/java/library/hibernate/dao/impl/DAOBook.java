package library.hibernate.dao.impl;

import library.hibernate.entities.Book;
import library.hibernate.entities.EntityBase;
import library.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import javax.naming.NamingException;
import java.sql.SQLException;
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
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    "WHERE upper(title) LIKE upper(" + book.getTitle() + ")")
                    .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return entities;
    }
}
