package library.dataAccess.accessPoint.active.hibernate.dao.impl;

import library.dataAccess.accessPoint.active.hibernate.dao.ManagerDAO;
import library.dataAccess.accessPoint.active.hibernate.entities.Author;
import library.dataAccess.accessPoint.active.hibernate.entities.Book;
import library.dataAccess.accessPoint.active.hibernate.entities.BookAuthor;
import library.dataAccess.hibernate.dao.impl.DAOBookAuthor;
import library.dataAccess.jdbc.dao.impl.JDBCManagerBookAuthor;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class DBManagerBookAuthor implements ManagerDAO<BookAuthor, Integer> {

    private DAOBookAuthor dao = new DAOBookAuthor();

    @Override
    public List<BookAuthor> getAll() {
        return dao.getAll();
    }

    @Override
    public BookAuthor getEntityById(Integer id) {
        return dao.getEntityById(id);
    }

    @Override
    public void update(BookAuthor entity) {
        dao.update(entity);
    }

    @Override
    public void delete(BookAuthor entity) {
        dao.delete(entity);
    }

    @Override
    public int create(BookAuthor entity) {
        return dao.create(entity);
    }

    @Override
    public List<BookAuthor> searchEntityByName(BookAuthor entity) {
        return dao.searchEntityByName(entity);
    }

    public List<Book> searchBooksByAuthor(Author entity) {
        return dao.searchBooksByAuthor(entity);
    }

    public List<Author> searchAuthorsByBook(Book entity) {
        return dao.searchAuthorsByBook(entity);
    }

}