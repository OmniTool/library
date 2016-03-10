package library.dao;

import javax.naming.NamingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface DBManager <E, K> {

    public abstract List<E> getAll() throws SQLException, NamingException;
    public abstract E getEntityById(K id) throws SQLException, NamingException;
    public abstract void update(E entity) throws SQLException, NamingException;
    public abstract void delete(E entity) throws SQLException, NamingException;
    public abstract void create(E entity) throws SQLException, NamingException;

}
