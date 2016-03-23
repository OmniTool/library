package library.dataAccess.accessPoint.dao.impl;

import library.dataAccess.jdbc.connectors.DBConnector;
import library.dataAccess.jdbc.connectors.DBConnectorPool;
import library.dataAccess.accessPoint.dao.ManagerDAO;
import library.dataAccess.accessPoint.entities.Author;
import library.dataAccess.accessPoint.entities.Book;
import library.dataAccess.accessPoint.entities.BookAuthor;
import library.dataAccess.jdbc.dao.impl.JDBCManagerBookAuthor;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManagerBookAuthor implements ManagerDAO<BookAuthor, Integer> {

    private JDBCManagerBookAuthor dao = new JDBCManagerBookAuthor();

    @Override
    public List<BookAuthor> getAll() throws SQLException, NamingException {
        return dao.getAll();
    }

    @Override
    public BookAuthor getEntityById(Integer id) throws SQLException, NamingException {
        return dao.getEntityById(id);
    }

    @Override
    public void update(BookAuthor entity) throws SQLException, NamingException {
        dao.update(entity);
    }

    @Override
    public void delete(BookAuthor entity) throws SQLException, NamingException {
        dao.delete(entity);
    }

    @Override
    public int create(BookAuthor entity) throws SQLException, NamingException {
        return dao.create(entity);
    }

    @Override
    public List<BookAuthor> searchEntityByName(BookAuthor entity) throws SQLException, NamingException {
        return dao.searchEntityByName(entity);
    }

    public List<Book> searchBooksByAuthor(Author entity) throws SQLException, NamingException {
        return dao.searchBooksByAuthor(entity);
    }

    public List<Author> searchAuthorsByBook(Book entity) throws SQLException, NamingException {
        return dao.searchAuthorsByBook(entity);
    }

}