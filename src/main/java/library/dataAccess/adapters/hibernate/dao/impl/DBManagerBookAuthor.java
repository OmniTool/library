package library.dataAccess.adapters.hibernate.dao.impl;

import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;
import library.dataAccess.adapters.hibernate.entities.BookAuthorAdapter;
import library.dataAccess.connectors.hibernate.dao.impl.DAOBookAuthor;
import library.dataAccess.connectors.hibernate.entities.BookAuthorHiber;
import library.dataAccess.connectors.hibernate.entities.EntityBaseHiber;

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
        return new BookAuthorAdapter((BookAuthorHiber) dao.getEntityById(id));
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
        List<EntityBaseHiber> list = dao.searchEntityByName(entity.getEntity());
        List<BookAuthorAdapter> booksAuthors = new ArrayList<>();
        for (EntityBaseHiber ebh : list) {
            booksAuthors.add(new BookAuthorAdapter((BookAuthorHiber) ebh));
        }
        return booksAuthors;
    }
    public List<BookAdapter> searchBooksByAuthor(AuthorAdapter entity) {
        return dao.searchBooksByAuthor(entity);
    }
    public List<AuthorAdapter> searchAuthorsByBook(BookAdapter entity) {
        return dao.searchAuthorsByBook(entity);
    }
}