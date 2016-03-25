package library.dataAccess.connectors.jdbc.dao;

import library.dataAccess.accessPoint.ManagerDAO;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<E, K> extends ManagerDAO<E, K> {

    List<E> getAll() throws SQLException, NamingException;
    E getEntityById(K id) throws SQLException, NamingException;
    void update(E entity) throws SQLException, NamingException;
    void delete(E entity) throws SQLException, NamingException;
    int create(E entity) throws SQLException, NamingException;
    List<E> searchEntityByName(E entity) throws SQLException, NamingException;

}
