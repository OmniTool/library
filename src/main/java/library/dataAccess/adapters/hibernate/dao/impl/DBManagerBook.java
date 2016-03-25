package library.dataAccess.adapters.hibernate.dao.impl;

import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;
import library.dataAccess.adapters.hibernate.entities.GenreAdapter;
import library.dataAccess.connectors.hibernate.dao.impl.DAOBook;
import library.dataAccess.connectors.hibernate.entities.BookHiber;
import library.dataAccess.connectors.hibernate.entities.EntityBaseHiber;

import java.util.ArrayList;
import java.util.List;

public class DBManagerBook implements ManagerDAO<BookAdapter, Integer> {

    private DAOBook dao = new DAOBook();

    @Override
    public List<BookAdapter> getAll() {
        return dao.getAll();
    }
    @Override
    public BookAdapter getEntityById(Integer id) {
        return new BookAdapter((BookHiber) dao.getEntityById(id));
    }
    @Override
    public void update(BookAdapter entity) {
        dao.update(entity.getEntity());
    }
    @Override
    public void delete(BookAdapter entity) {
        dao.delete(entity.getEntity());
    }
    @Override
    public int create(BookAdapter entity) {
        return dao.create(entity.getEntity());
    }
    @Override
    public List<BookAdapter> searchEntityByName(BookAdapter entity) {
        List<EntityBaseHiber> list = dao.searchEntityByName(entity.getEntity());
        List<BookAdapter> books = new ArrayList<>();
        for (EntityBaseHiber ebh : list) {
            books.add(new BookAdapter((BookHiber) ebh));
        }
        return books;
    }
    public List<BookAdapter> searchBooksByGenre(GenreAdapter genre) {
        List<BookAdapter> entities = new ArrayList<>();
        for (BookHiber book : dao.searchBooksByGenre(genre.getEntity()))
            entities.add(new BookAdapter(book));
        return entities;
    }
}
