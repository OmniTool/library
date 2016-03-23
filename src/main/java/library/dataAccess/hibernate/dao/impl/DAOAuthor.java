package library.dataAccess.hibernate.dao.impl;

import library.dataAccess.hibernate.entities.Author;
import library.dataAccess.hibernate.entities.EntityBase;
import library.dataAccess.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DAOAuthor extends BaseDAOImpl {
    public DAOAuthor() {
        super(Author.class);
    }

    @Override
    public List<EntityBase> searchEntityByName(EntityBase entity) {
        Author author = (Author) entity;
        Session session = null;
        List<EntityBase> entities = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    "WHERE upper(first_name) LIKE upper(" + author.getFirstName() + ") " +
                    "AND upper(second_name) LIKE upper(" + author.getSecondName() + ") " +
                    "AND upper(middle_name) LIKE upper(" + author.getMiddleName() + ")")
                    .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return entities;
    }
}
