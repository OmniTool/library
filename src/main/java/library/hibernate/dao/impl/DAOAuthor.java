package library.hibernate.dao.impl;

import library.hibernate.entities.Author;
import library.hibernate.entities.EntityBase;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class DAOAuthor extends BaseDAOImpl {
    public DAOAuthor() {
        super(Author.class);
    }

    @Override
    public List<EntityBase> searchEntityByName(EntityBase entity) {
        return null;
    }
}
