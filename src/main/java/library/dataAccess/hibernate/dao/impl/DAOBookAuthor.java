package library.dataAccess.hibernate.dao.impl;

import library.dataAccess.hibernate.entities.BookAuthorHiber;
import library.dataAccess.hibernate.entities.EntityBaseHiber;
import library.dataAccess.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DAOBookAuthor extends BaseDAOImpl {
    public DAOBookAuthor() {
        super(BookAuthorHiber.class);
    }

    @Override
    public List<EntityBaseHiber> searchEntityByName(EntityBaseHiber entity) {
        BookAuthorHiber bookAuthor = (BookAuthorHiber) entity;
        Session session = null;
        List<EntityBaseHiber> entities = new ArrayList<>();
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