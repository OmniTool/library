package library.dataAccess.connectors.jdbc.dao.impl;

import library.dataAccess.connectors.jdbc.util.DBConnector;
import library.dataAccess.connectors.jdbc.util.DBConnectorPool;
import library.dataAccess.connectors.jdbc.dao.DAOJDBC;
import library.dataAccess.adapters.jdbc.entities.*;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCManagerAuthor implements DAOJDBC<Author, Integer> {

    private static DBConnector connector = new DBConnectorPool();

    @Override
    public List<Author> getAll() throws SQLException, NamingException {
        String statementSQL = "SELECT * FROM authors";
        List<Author> list = new ArrayList<>();
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Author entity = new Author();
                entity.setId(rs.getInt("id"));
                entity.setSecondName(rs.getString("second_name"));
                entity.setFirstName(rs.getString("first_name"));
                entity.setMiddleName(rs.getString("middle_name"));
                entity.setBirthYear(rs.getInt("birth_year"));
                entity.setBiography(rs.getString("biography"));
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
    public Author getEntityById(Integer id) throws SQLException, NamingException {
        String statementSQL = "SELECT * FROM authors WHERE id = ?";
        Author entity = new Author();
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                entity.setId(rs.getInt("id"));
                entity.setSecondName(rs.getString("second_name"));
                entity.setFirstName(rs.getString("first_name"));
                entity.setMiddleName(rs.getString("middle_name"));
                entity.setBirthYear(rs.getInt("birth_year"));
                entity.setBiography(rs.getString("biography"));
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
    public void update(Author entity) throws SQLException, NamingException {
        String statementSQL = "UPDATE authors SET second_name = ?, first_name = ?, middle_name = ?, birth_year = ?, biography = ? WHERE id = ?";
        Connection connection = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            preparedStatement.setString(1, entity.getSecondName());
            preparedStatement.setString(2, entity.getFirstName());
            preparedStatement.setString(3, entity.getMiddleName());
            preparedStatement.setInt(4, entity.getBirthYear());
            preparedStatement.setString(5, entity.getBiography());
            preparedStatement.setInt(6, entity.getId());
            preparedStatement.executeUpdate();
        } finally {
            if (connection != null)
                connection.close();
        }
    }
    @Override
    public void delete(Author entity) throws SQLException, NamingException {
        String statementSQL = "DELETE FROM authors WHERE id = ?";
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
    public int create(Author entity) throws SQLException, NamingException {
        String statementSQL = "INSERT INTO authors (second_name, first_name, middle_name, birth_year, biography) VALUES (?, ?, ?, ?, ?)";
        String nextvalSQL = "SELECT nextval('increment_id')";
        int futureId = 0;
        Connection connection = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            preparedStatement.setString(1, entity.getSecondName());
            preparedStatement.setString(2, entity.getFirstName());
            preparedStatement.setString(3, entity.getMiddleName());
            preparedStatement.setInt(4, entity.getBirthYear());
            preparedStatement.setString(5, entity.getBiography());
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatementNextval = connection.prepareStatement(nextvalSQL);
            ResultSet rs = preparedStatementNextval.executeQuery();
            if (rs.next()) {
                futureId = rs.getInt("nextval");
            }
        } finally {
        if (connection != null)
            connection.close();
        }
        return --futureId;
    }
    @Override
    public List<Author> searchEntityByName(Author entity) throws SQLException, NamingException {
        String statementSQL = "SELECT * FROM authors WHERE upper(first_name) LIKE upper(?) AND upper(second_name) LIKE upper(?) AND upper(middle_name) LIKE upper(?)";
        List<Author> list = new ArrayList<>();
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            preparedStatement.setString(1, "%" + entity.getFirstName() + "%");
            preparedStatement.setString(2, "%" + entity.getSecondName() + "%");
            preparedStatement.setString(3, "%" + entity.getMiddleName() + "%");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Author author = new Author();
                author.setId(rs.getInt("id"));
                author.setSecondName(rs.getString("second_name"));
                author.setFirstName(rs.getString("first_name"));
                author.setMiddleName(rs.getString("middle_name"));
                author.setBirthYear(rs.getInt("birth_year"));
                author.setBiography(rs.getString("biography"));
                list.add(author);
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
