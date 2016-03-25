package library.dataAccess.connectors.jdbc.dao.impl;

import library.dataAccess.connectors.jdbc.entities.Genre;
import library.dataAccess.connectors.jdbc.util.DBConnector;
import library.dataAccess.connectors.jdbc.util.DBConnectorPool;
import library.dataAccess.connectors.jdbc.dao.BaseDAO;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOGenre implements BaseDAO<Genre, Integer> {

    private static DBConnector connector = new DBConnectorPool();

    @Override
    public List<Genre> getAll() throws SQLException, NamingException {
        String statementSQL = "SELECT * FROM genres";
        List<Genre> list = new ArrayList<>();
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Genre entity = new Genre();
                entity.setId(rs.getInt("id"));
                entity.setTitle(rs.getString("title"));
                entity.setDescription(rs.getString("description"));
                list.add(entity);
            }
            return list;
        } finally {
            if (connection != null)
                connection.close();
            if (rs != null)
                rs.close();
        }
    }
    @Override
    public Genre getEntityById(Integer id) throws SQLException, NamingException {
        String statementSQL = "SELECT * FROM genres WHERE id = ?";
        Genre entity = new Genre();
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                entity.setId(rs.getInt("id"));
                entity.setTitle(rs.getString("title"));
                entity.setDescription(rs.getString("description"));
            }
            return entity;
        } finally {
            if (connection != null)
                connection.close();
            if (rs != null)
                rs.close();
        }
    }
    @Override
    public void update(Genre entity) throws SQLException, NamingException {
        String statementSQL = "UPDATE genres SET title = ?, description = ? WHERE id = ?";
        Connection connection = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();
        } finally {
            if (connection != null)
                connection.close();
        }
    }
    @Override
    public void delete(Genre entity) throws SQLException, NamingException {
        String statementSQL = "DELETE FROM genres WHERE id = ?";
        Connection connection = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.executeUpdate();
        } finally {
            if (connection != null)
                connection.close();
        }
    }
    @Override
    public int create(Genre entity) throws SQLException, NamingException {
        String statementSQL = "INSERT INTO genres (title, description) VALUES (?, ?)";
        Connection connection = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.executeUpdate();
        } finally {
        if (connection != null)
            connection.close();
        }
        return 0;
    }
    @Override
    public List<Genre> searchEntityByName(Genre entity) throws SQLException, NamingException {
        String statementSQL = "SELECT * FROM genres WHERE upper(title) LIKE upper(?)";
        List<Genre> list = new ArrayList<>();
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            preparedStatement.setString(1, "%" + entity.getTitle() + "%");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Genre genre = new Genre();
                genre.setId(rs.getInt("id"));
                genre.setTitle(rs.getString("title"));
                genre.setDescription(rs.getString("description"));
                list.add(genre);
            }
            return list;
        } finally {
            if (connection != null)
                connection.close();
            if (rs != null)
                rs.close();
        }
    }
}
