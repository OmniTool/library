package library.hibernate.dao.impl;

import library.hibernate.entities.Book;
import library.hibernate.entities.EntityBase;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class DAOBook extends BaseDAOImpl {
    public DAOBook() {
        super(Book.class);
    }

    @Override
    public List<EntityBase> searchEntityByName(EntityBase entity) {
        return null;
    }
}
