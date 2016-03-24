package library.dataAccess.adapters.jdbc.dao.impl;

import library.dataAccess.adapters.jdbc.dao.ManagerDAOJDBC;
import library.dataAccess.adapters.jdbc.entities.*;
import library.dataAccess.connectors.jdbc.dao.impl.JDBCManagerBook;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class DBManagerBook implements ManagerDAOJDBC<Book, Integer> {

    private JDBCManagerBook dao = new JDBCManagerBook();

    @Override
    public List<Book> getAll() throws SQLException, NamingException {
        return dao.getAll();
    }

    @Override
    public Book getEntityById(Integer id) throws SQLException, NamingException {
        return dao.getEntityById(id);
    }

    @Override
    public void update(Book entity) throws SQLException, NamingException {
        dao.update(entity);
    }

    @Override
    public void delete(Book entity) throws SQLException, NamingException {
        dao.delete(entity);
    }

    @Override
    public int create(Book entity) throws SQLException, NamingException {
        return dao.create(entity);
    }

    @Override
    public List<Book> searchEntityByName(Book entity) throws SQLException, NamingException {
        return dao.searchEntityByName(entity);
    }

}
