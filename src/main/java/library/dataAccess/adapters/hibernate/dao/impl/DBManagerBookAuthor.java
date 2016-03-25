package library.dataAccess.adapters.hibernate.dao.impl;

import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;
import library.dataAccess.adapters.hibernate.entities.BookAuthorAdapter;
import library.dataAccess.connectors.hibernate.dao.impl.DAOBookAuthor;
import library.dataAccess.connectors.hibernate.entities.Author;
import library.dataAccess.connectors.hibernate.entities.Book;
import library.dataAccess.connectors.hibernate.entities.BookAuthor;
import library.dataAccess.connectors.hibernate.entities.EntityBase;

import java.util.ArrayList;
import java.util.List;

public class DBManagerBookAuthor implements ManagerDAO<BookAuthorAdapter, Integer> {

    private DAOBookAuthor dao = new DAOBookAuthor();

    @Override
    public List<BookAuthorAdapter> getAll() {
        return dao.getAll();
    }
    @Override
    public BookAuthorAdapter getEntityById(Integer id) {
        return new BookAuthorAdapter((BookAuthor) dao.getEntityById(id));
    }
    @Override
    public void update(BookAuthorAdapter entity) {
        dao.update(entity.getEntity());
    }
    @Override
    public void delete(BookAuthorAdapter entity) {
        dao.delete(entity.getEntity());
    }
    @Override
    public int create(BookAuthorAdapter entity) {
        return dao.create(entity.getEntity());
    }
    @Override
    public List<BookAuthorAdapter> searchEntityByName(BookAuthorAdapter entity) {
        List<EntityBase> list = dao.searchEntityByName(entity.getEntity());
        List<BookAuthorAdapter> booksAuthors = new ArrayList<>();
        for (EntityBase ebh : list) {
            booksAuthors.add(new BookAuthorAdapter((BookAuthor) ebh));
        }
        return booksAuthors;
    }
    public List<BookAdapter> searchBooksByAuthor(AuthorAdapter entity) {
        List<BookAdapter> books = new ArrayList<>();
        for (Book e : dao.searchBooksByAuthor(entity.getEntity()))
            books.add(new BookAdapter(e));
        return books;
    }
    public List<AuthorAdapter> searchAuthorsByBook(BookAdapter entity) {
        List<AuthorAdapter> books = new ArrayList<>();
        for (Author e : dao.searchAuthorsByBook(entity.getEntity()))
            books.add(new AuthorAdapter(e));
        return books;
    }
}