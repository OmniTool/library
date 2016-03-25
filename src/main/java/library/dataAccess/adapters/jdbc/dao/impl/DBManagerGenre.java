package library.dataAccess.adapters.jdbc.dao.impl;

import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.jdbc.entities.Genre;
import library.dataAccess.connectors.jdbc.dao.impl.DAOGenre;

import javax.naming.NamingException;
import java.sql.*;
import java.util.List;

public class DBManagerGenre implements ManagerDAO<Genre, Integer> {

    private DAOGenre dao = new DAOGenre();

    @Override
    public List<Genre> getAll() throws SQLException, NamingException {
    return dao.getAll();
    }
    @Override
    public Genre getEntityById(Integer id) throws SQLException, NamingException {
    return dao.getEntityById(id);
    }
    @Override
    public void update(Genre entity) throws SQLException, NamingException {
        dao.update(entity);
    }
    @Override
    public void delete(Genre entity) throws SQLException, NamingException {
        dao.delete(entity);
    }
    @Override
    public int create(Genre entity) throws SQLException, NamingException {
        return dao.create(entity);
    }
    @Override
    public List<Genre> searchEntityByName(Genre entity) throws SQLException, NamingException {
        return dao.searchEntityByName(entity);
    }
}
