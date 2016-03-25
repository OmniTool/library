package library.dataAccess.adapters.jdbc.dao.impl;

import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.jdbc.entities.GenreAdapter;
import library.dataAccess.connectors.jdbc.dao.impl.DAOGenre;

import javax.naming.NamingException;
import java.sql.*;
import java.util.List;

public class DBManagerGenre implements ManagerDAO<GenreAdapter, Integer> {

    private DAOGenre dao = new DAOGenre();

    @Override
    public List<GenreAdapter> getAll() throws SQLException, NamingException {
    return dao.getAll();
    }
    @Override
    public GenreAdapter getEntityById(Integer id) throws SQLException, NamingException {
    return new GenreAdapter(dao.getEntityById(id));
    }
    @Override
    public void update(GenreAdapter entity) throws SQLException, NamingException {
        dao.update(entity);
    }
    @Override
    public void delete(GenreAdapter entity) throws SQLException, NamingException {
        dao.delete(entity);
    }
    @Override
    public int create(GenreAdapter entity) throws SQLException, NamingException {
        return dao.create(entity);
    }
    @Override
    public List<GenreAdapter> searchEntityByName(GenreAdapter entity) throws SQLException, NamingException {
        return dao.searchEntityByName(entity);
    }
}
