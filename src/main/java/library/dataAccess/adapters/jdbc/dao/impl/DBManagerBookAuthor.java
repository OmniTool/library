package library.dataAccess.adapters.jdbc.dao.impl;

import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.jdbc.entities.AuthorAdapter;
import library.dataAccess.adapters.jdbc.entities.BookAdapter;
import library.dataAccess.adapters.jdbc.entities.BookAuthorAdapter;
import library.dataAccess.connectors.jdbc.dao.impl.DAOBookAuthor;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class DBManagerBookAuthor implements ManagerDAO<BookAuthorAdapter, Integer> {

    private DAOBookAuthor dao = new DAOBookAuthor();

    @Override
    public List<BookAuthorAdapter> getAll() throws SQLException, NamingException {
        return dao.getAll();
    }
    @Override
    public BookAuthorAdapter getEntityById(Integer id) throws SQLException, NamingException {
        return dao.getEntityById(id);
    }
    @Override
    public void update(BookAuthorAdapter entity) throws SQLException, NamingException {
        dao.update(entity);
    }
    @Override
    public void delete(BookAuthorAdapter entity) throws SQLException, NamingException {
        dao.delete(entity);
    }
    @Override
    public int create(BookAuthorAdapter entity) throws SQLException, NamingException {
        return dao.create(entity);
    }
    @Override
    public List<BookAuthorAdapter> searchEntityByName(BookAuthorAdapter entity) throws SQLException, NamingException {
        return dao.searchEntityByName(entity);
    }
    public List<BookAdapter> searchBooksByAuthor(AuthorAdapter entity) throws SQLException, NamingException {
        return dao.searchBooksByAuthor(entity);
    }
    public List<AuthorAdapter> searchAuthorsByBook(BookAdapter entity) throws SQLException, NamingException {
        return dao.searchAuthorsByBook(entity);
    }
}