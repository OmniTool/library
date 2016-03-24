package library.dataAccess.hibernate.dao.impl;

import library.dataAccess.hibernate.entities.BookHiber;
import library.dataAccess.hibernate.entities.EntityBaseHiber;
import library.dataAccess.hibernate.util.HibernateUtil;
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
            session = HibernateUtil.getSessionFactory().openSession();
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    " e WHERE upper(e.title) LIKE upper(:title)")
                    .setParameter("title", "%" + book.getTitle() + "%")
                    .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return entities;
    }
}
