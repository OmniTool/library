package library.dao;

import library.dao.entities.BookAuthor;

import javax.naming.NamingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface ManagerDAO <E, K> {

    List<E> getAll() throws SQLException, NamingException;
    E getEntityById(K id) throws SQLException, NamingException;
    void update(E entity) throws SQLException, NamingException;
    void delete(E entity) throws SQLException, NamingException;
    void create(E entity) throws SQLException, NamingException;
    List<E> searchEntityByName(E entity) throws SQLException, NamingException;

}
