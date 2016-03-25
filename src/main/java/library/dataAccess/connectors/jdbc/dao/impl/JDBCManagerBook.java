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

public class JDBCManagerBook implements DAOJDBC<Book, Integer> {

    DBConnector connector = new DBConnectorPool();

    @Override
    public List<Book> getAll() throws SQLException, NamingException {
        String statementSQL = "SELECT * FROM books";
        List<Book> list = new ArrayList<>();
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Book entity = new Book();
                entity.setId(rs.getInt("id"));
                entity.setTitle(rs.getString("title"));
                entity.setPubYear(rs.getInt("pub_year"));
                entity.setGenereId(rs.getInt("genere_id"));
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
    public Book getEntityById(Integer id) throws SQLException, NamingException {
        String statementSQL = "SELECT * FROM books WHERE id = ?";
        Book entity = new Book();
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
                entity.setPubYear(rs.getInt("pub_year"));
                entity.setGenereId(rs.getInt("genere_id"));
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
    public void update(Book entity) throws SQLException, NamingException {
        String statementSQL = "UPDATE books SET title = ?, pub_year = ?, genere_id = ? WHERE id = ?";
        Connection connection = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setInt(2, entity.getPubYear());
            preparedStatement.setInt(3, entity.getGenereId());
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.executeUpdate();
        } finally {
            if (connection != null)
                connection.close();
        }
    }
    @Override
    public void delete(Book entity) throws SQLException, NamingException {
        String statementSQL = "DELETE FROM books WHERE id = ?";
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
    public int create(Book entity) throws SQLException, NamingException {
        String statementSQL = "INSERT INTO books (title, pub_year, genere_id) VALUES (?, ?, ?)";
        String nextvalSQL = "SELECT nextval('increment_id')";
        int futureId = 0;
        Connection connection = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setInt(2, entity.getPubYear());
            preparedStatement.setInt(3, entity.getGenereId());
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
    public List<Book> searchEntityByName(Book entity) throws SQLException, NamingException {
        String statementSQL = "SELECT * FROM books WHERE upper(title) LIKE upper(?)";
        List<Book> list = new ArrayList<>();
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            preparedStatement.setString(1, "%" + entity.getTitle() + "%");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setPubYear(rs.getInt("pub_year"));
                book.setGenereId(rs.getInt("genere_id"));
                list.add(book);
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
