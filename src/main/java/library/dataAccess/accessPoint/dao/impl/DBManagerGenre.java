package library.dataAccess.accessPoint.dao.impl;

import library.dataAccess.jdbc.connectors.DBConnector;
import library.dataAccess.jdbc.connectors.DBConnectorPool;
import library.dataAccess.accessPoint.dao.ManagerDAO;
import library.dataAccess.accessPoint.entities.Genre;
import library.dataAccess.jdbc.dao.impl.JDBCManagerGenre;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManagerGenre implements ManagerDAO<Genre, Integer> {

    private JDBCManagerGenre dao = new JDBCManagerGenre();

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
