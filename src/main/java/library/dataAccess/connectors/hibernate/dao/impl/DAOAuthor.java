package library.dataAccess.connectors.hibernate.dao.impl;

import library.dataAccess.connectors.hibernate.entities.AuthorHiber;
import library.dataAccess.connectors.hibernate.entities.EntityBaseHiber;
import library.dataAccess.connectors.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DAOAuthor extends BaseDAOImpl {
    public DAOAuthor() {
        super(AuthorHiber.class);
    }

    @Override
    public List<EntityBaseHiber> searchEntityByName(EntityBaseHiber entity) {
        AuthorHiber author = (AuthorHiber) entity;
        Session session = null;
        List<EntityBaseHiber> entities = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    " e WHERE upper(e.firstName) LIKE upper(:first_name) " +
                    "AND upper(e.secondName) LIKE upper(:second_name) " +
                    "AND upper(e.middleName) LIKE upper(:middle_name)")
                    .setParameter("first_name", "%" + author.getFirstName() + "%")
                    .setParameter("second_name", "%" + author.getSecondName()+ "%")
                    .setParameter("middle_name", "%" + author.getMiddleName() + "%")
                    .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return entities;
    }
}
