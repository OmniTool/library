package library.dataAccess.adapters.hibernate.dao.impl;

import library.dataAccess.adapters.hibernate.dao.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.Book;
import library.dataAccess.adapters.hibernate.entities.Genre;
import library.dataAccess.connectors.hibernate.dao.impl.DAOBook;
import library.dataAccess.connectors.hibernate.entities.BookHiber;
import library.dataAccess.connectors.hibernate.entities.EntityBaseHiber;

import java.util.ArrayList;
import java.util.List;

public class DBManagerBook implements ManagerDAO<Book, Integer> {

    private DAOBook dao = new DAOBook();

    @Override
    public List<Book> getAll() {
        return dao.getAll();
    }

    @Override
    public Book getEntityById(Integer id) {
        return new Book((BookHiber) dao.getEntityById(id));
    }

    @Override
    public void update(Book entity) {
        dao.update(entity.getEntity());
    }

    @Override
    public void delete(Book entity) {
        dao.delete(entity.getEntity());
    }

    @Override
    public int create(Book entity) {
        return dao.create(entity.getEntity());
    }

    @Override
    public List<Book> searchEntityByName(Book entity) {
        List<EntityBaseHiber> list = dao.searchEntityByName(entity.getEntity());
        List<Book> books = new ArrayList<>();
        for (EntityBaseHiber ebh : list) {
            books.add(new Book((BookHiber) ebh));
        }
        return books;
    }

    public List<Book> searchBooksByGenre(Genre genre) {
        List<Book> entities = new ArrayList<>();
        for (BookHiber book : dao.searchBooksByGenre(genre.getEntity()))
            entities.add(new Book(book));
        return entities;
    }

}