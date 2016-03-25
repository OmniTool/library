package library.dataAccess.connectors.hibernate.dao.impl;

import library.dataAccess.connectors.hibernate.entities.Author;
import library.dataAccess.connectors.hibernate.entities.EntityBase;
import library.dataAccess.connectors.hibernate.util.HibernateUtil;
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
            session = HibernateUtil.getSession();
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    " e WHERE upper(e.firstName) LIKE upper(:first_name) " +
                    "AND upper(e.secondName) LIKE upper(:second_name) " +
                    "AND upper(e.middleName) LIKE upper(:middle_name)")
                    .setParameter("first_name", "%" + author.getFirstName() + "%")
                    .setParameter("second_name", "%" + author.getSecondName()+ "%")
                    .setParameter("middle_name", "%" + author.getMiddleName() + "%")
                    .list();
        } finally {HibernateUtil.close(session);}
        return entities;
    }
}
