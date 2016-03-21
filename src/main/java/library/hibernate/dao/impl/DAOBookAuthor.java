package library.hibernate.dao.impl;

import library.hibernate.entities.BookAuthor;
import library.hibernate.entities.EntityBase;
import library.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOBookAuthor extends BaseDAOImpl {
    public DAOBookAuthor() {
        super(BookAuthor.class);
    }

    @Override
    public List<EntityBase> searchEntityByName(EntityBase entity) {
        BookAuthor bookAuthor = (BookAuthor) entity;
        Session session = null;
        List<EntityBase> entities = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession(); //TODO
//            entities = session.createQuery("FROM " + type.getSimpleName() +
//                    "WHERE upper(first_name) LIKE upper(" + bookAuthor.() + ") " +
//                    "AND upper(middle_name) LIKE upper(" + bookAuthor.() + ")")
//                    .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return entities;
    }
}