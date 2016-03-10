package library.dao;

import library.dao.connectors.DBConnector;
import library.dao.connectors.DBConnectorPool;
import library.dao.entities.Genre;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManagerGenre implements ManagerDAO <Genre, Integer> {

    DBConnector connector = new DBConnectorPool();

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

    @Override
    public Genre getEntityById(Integer id) throws SQLException, NamingException {

        String statementSQL = "SELECT * FROM genres WHERE id = ?";
        Genre genre = new Genre();

        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                genre.setId(rs.getInt("id"));
                genre.setTitle(rs.getString("title"));
                genre.setDescription(rs.getString("description"));
            }
            return genre;
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
            preparedStatement.setString(1, "'" + entity.getTitle() + "'");
            preparedStatement.setString(2, "'" + entity.getDescription() + "'");
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
    public void create(Genre entity) throws SQLException, NamingException {

        String statementSQL = "INSERT INTO genres (title, description) VALUES (?, ?)";

        Connection connection = null;

        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            preparedStatement.setString(1, "'" + entity.getTitle() + "'");
            preparedStatement.setString(2, "'" + entity.getDescription() + "'");
            preparedStatement.executeUpdate();
        } finally {
        if (connection != null)
            connection.close();
        }
    }
}
