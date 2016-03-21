package library.hibernate.dao.impl;

import library.hibernate.entities.EntityBase;
import library.hibernate.entities.Genre;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class DAOGenre extends BaseDAOImpl {
    public DAOGenre() {
        super(Genre.class);
    }

    @Override
    public List<EntityBase> searchEntityByName(EntityBase entity) {
        return null;
    }
}
