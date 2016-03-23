package library.dataAccess.dao.impl;

import library.dataAccess.entities.Book;
import library.dataAccess.entities.BookAuthor;
import library.dataAccess.jdbc.connectors.DBConnector;
import library.dataAccess.jdbc.connectors.DBConnectorPool;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManagerBookAuthor implements library.dataAccess.dao.ManagerDAO<library.dataAccess.entities.BookAuthor, Integer> {

    DBConnector connector = new DBConnectorPool();
    
    @Override
    public List<library.dataAccess.entities.BookAuthor> getAll() throws SQLException, NamingException {

        String statementSQL = "SELECT * FROM books_authors";
        List<BookAuthor> list = new ArrayList<>();

        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                BookAuthor entity = new library.dataAccess.entities.BookAuthor();
                entity.setId(rs.getInt("id"));
                entity.setBookId(rs.getInt("book_id"));
                entity.setAuthorId(rs.getInt("author_id"));
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
    public library.dataAccess.entities.BookAuthor getEntityById(Integer id) throws SQLException, NamingException {

        String statementSQL = "SELECT * FROM books_authors WHERE id = ?";
        library.dataAccess.entities.BookAuthor entity = new library.dataAccess.entities.BookAuthor();

        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);

            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                entity.setId(rs.getInt("id"));
                entity.setBookId(rs.getInt("book_id"));
                entity.setAuthorId(rs.getInt("author_id"));
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
    public void update(library.dataAccess.entities.BookAuthor entity) throws SQLException, NamingException {

        String statementSQL = "UPDATE books_authors SET book_id = ?, author_id = ? WHERE id = ?";

        Connection connection = null;

        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);

            preparedStatement.setInt(1, entity.getBookId());
            preparedStatement.setInt(2, entity.getAuthorId());
            preparedStatement.setInt(3, entity.getId());

            preparedStatement.executeUpdate();
        } finally {
            if (connection != null)
                connection.close();
        }
    }

    @Override
    public void delete(library.dataAccess.entities.BookAuthor entity) throws SQLException, NamingException {

        String statementSQL = "DELETE FROM books_authors WHERE id = ?";

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
    public int create(library.dataAccess.entities.BookAuthor entity) throws SQLException, NamingException {

        String statementSQL = "INSERT INTO books_authors (book_id, author_id) VALUES (?, ?)";
        //String statementSQL = "INSERT INTO books_authors (title, pub_year, genere_id) VALUES ('русская книга из запроса eeeeeelllkkkkk', 1234, 55)";
        String nextval = "SELECT nextval('books_authors_id')";

        Connection connection = null;

        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);

            preparedStatement.setInt(1, entity.getBookId());
            preparedStatement.setInt(2, entity.getAuthorId());

            preparedStatement.executeUpdate();
        } finally {
            if (connection != null)
                connection.close();
        }
        return 0;
    }

    @Override
    public List<library.dataAccess.entities.BookAuthor> searchEntityByName(library.dataAccess.entities.BookAuthor entity) throws SQLException, NamingException {
        List<library.dataAccess.entities.BookAuthor> list = new ArrayList<>();

        String statementSQL = "SELECT * FROM books_authors WHERE author_id = ? AND book_id = ?";
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            preparedStatement.setInt(1, entity.getAuthorId());
            preparedStatement.setInt(2, entity.getBookId());
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                library.dataAccess.entities.BookAuthor ba = new library.dataAccess.entities.BookAuthor();
                ba.setId(rs.getInt("id"));
                ba.setBookId(rs.getInt("book_id"));
                ba.setAuthorId(rs.getInt("author_id"));
                list.add(ba);
            }
            return list;
        } finally {
            if (connection != null)
                connection.close();
            if (rs != null)
                rs.close();
        }
    }

    public List<library.dataAccess.entities.Book> searchBooksByAuthor(library.dataAccess.entities.Author entity) throws SQLException, NamingException {
        //List<Integer> listId = new ArrayList<>();
        List<Book> list = new ArrayList<>();
        DBManagerBook dao = new library.dataAccess.dao.impl.DBManagerBook();
        String statementSQL = "SELECT * FROM books_authors WHERE author_id = ?";
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            preparedStatement.setInt(1, entity.getId());
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("book_id");
                list.add(dao.getEntityById(id));
            }
            return list;
        } finally {
            if (connection != null)
                connection.close();
            if (rs != null)
                rs.close();
        }

    }

    public List<library.dataAccess.entities.Author> searchAuthorsByBook(library.dataAccess.entities.Book entity) throws SQLException, NamingException {
        //List<Integer> listId = new ArrayList<>();
        List<library.dataAccess.entities.Author> list = new ArrayList<>();
        library.dataAccess.dao.impl.DBManagerAuthor dao = new library.dataAccess.dao.impl.DBManagerAuthor();
        String statementSQL = "SELECT * FROM books_authors WHERE book_id = ?";
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statementSQL);
            preparedStatement.setInt(1, entity.getId());
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("author_id");
                list.add(dao.getEntityById(id));
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