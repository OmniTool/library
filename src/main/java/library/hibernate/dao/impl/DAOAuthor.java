package library.hibernate.dao.impl;

import library.hibernate.entities.Author;
import library.hibernate.entities.EntityBase;
import library.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import javax.management.Query;
import javax.naming.NamingException;
import java.sql.SQLException;
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
