package library.dataAccess.adapters.jdbc.dao.impl;

import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.jdbc.entities.*;
import library.dataAccess.connectors.jdbc.dao.impl.DAOBook;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class DBManagerBook implements ManagerDAO<BookAdapter, Integer> {

    private DAOBook dao = new DAOBook();

    @Override
    public List<BookAdapter> getAll() throws SQLException, NamingException {
        return dao.getAll();
    }
    @Override
    public BookAdapter getEntityById(Integer id) throws SQLException, NamingException {
        return dao.getEntityById(id);
    }
    @Override
    public void update(BookAdapter entity) throws SQLException, NamingException {
        dao.update(entity);
    }
    @Override
    public void delete(BookAdapter entity) throws SQLException, NamingException {
        dao.delete(entity);
    }
    @Override
    public int create(BookAdapter entity) throws SQLException, NamingException {
        return dao.create(entity);
    }
    @Override
    public List<BookAdapter> searchEntityByName(BookAdapter entity) throws SQLException, NamingException {
        return dao.searchEntityByName(entity);
    }
}
