package library.dao;

import library.dao.connectors.DBConnector;
import library.dao.connectors.DBConnectorPool;
import library.dao.entities.Book;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManagerBook implements ManagerDAO <Book, Integer> {

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

    @Override
    public Book getEntityById(Integer id) throws SQLException, NamingException {

        String statementSQL = "SELECT * FROM books WHERE id = ?";
        Book book = new Book();

        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);

            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setPubYear(rs.getInt("pub_year"));
                book.setGenereId(rs.getInt("genere_id"));
            }
            return book;
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
    public void create(Book entity) throws SQLException, NamingException {

        String statementSQL = "INSERT INTO books (title, pub_year, genere_id) VALUES (?, ?, ?)";

        Connection connection = null;

        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);

            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setInt(2, entity.getPubYear());
            preparedStatement.setInt(3, entity.getGenereId());

            preparedStatement.executeUpdate();
        } finally {
        if (connection != null)
            connection.close();
        }
    }
}
