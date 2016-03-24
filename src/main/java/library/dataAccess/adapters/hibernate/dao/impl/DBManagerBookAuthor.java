package library.dataAccess.adapters.hibernate.dao.impl;

import library.dataAccess.adapters.hibernate.dao.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.Author;
import library.dataAccess.adapters.hibernate.entities.Book;
import library.dataAccess.adapters.hibernate.entities.BookAuthor;
import library.dataAccess.connectors.hibernate.dao.impl.DAOBookAuthor;
import library.dataAccess.connectors.hibernate.entities.BookAuthorHiber;
import library.dataAccess.connectors.hibernate.entities.EntityBaseHiber;

import java.util.ArrayList;
import java.util.List;

public class DBManagerBookAuthor implements ManagerDAO<BookAuthor, Integer> {

    private DAOBookAuthor dao = new DAOBookAuthor();

    @Override
    public List<BookAuthor> getAll() {
        return dao.getAll();
    }
    @Override
    public BookAuthor getEntityById(Integer id) {
        return new BookAuthor((BookAuthorHiber) dao.getEntityById(id));
    }
    @Override
    public void update(BookAuthor entity) {
        dao.update(entity.getEntity());
    }
    @Override
    public void delete(BookAuthor entity) {
        dao.delete(entity.getEntity());
    }
    @Override
    public int create(BookAuthor entity) {
        return dao.create(entity.getEntity());
    }
    @Override
    public List<BookAuthor> searchEntityByName(BookAuthor entity) {
        List<EntityBaseHiber> list = dao.searchEntityByName(entity.getEntity());
        List<BookAuthor> booksAuthors = new ArrayList<>();
        for (EntityBaseHiber ebh : list) {
            booksAuthors.add(new BookAuthor((BookAuthorHiber) ebh));
        }
        return booksAuthors;
    }
    public List<Book> searchBooksByAuthor(Author entity) {
        return dao.searchBooksByAuthor(entity);
    }
    public List<Author> searchAuthorsByBook(Book entity) {
        return dao.searchAuthorsByBook(entity);
    }
}