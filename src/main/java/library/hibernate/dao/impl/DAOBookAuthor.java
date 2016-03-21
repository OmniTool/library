package library.hibernate.dao.impl;

import library.hibernate.entities.BookAuthor;
import library.hibernate.entities.EntityBase;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class DAOBookAuthor extends BaseDAOImpl {
    public DAOBookAuthor() {
        super(BookAuthor.class);
    }

    @Override
    public List<EntityBase> searchEntityByName(EntityBase entity) {
        return null;
    }
}