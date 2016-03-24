package library.dataAccess.adapters.hibernate.dao.impl;

import library.dataAccess.adapters.hibernate.dao.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.Author;
import library.dataAccess.connectors.hibernate.dao.impl.DAOAuthor;
import library.dataAccess.connectors.hibernate.entities.AuthorHiber;
import library.dataAccess.connectors.hibernate.entities.EntityBaseHiber;

import java.util.ArrayList;
import java.util.List;

public class DBManagerAuthor implements ManagerDAO<Author, Integer> {

    private DAOAuthor dao = new DAOAuthor();

    @Override
    public List<Author> getAll() {
        return dao.getAll();
    }
    @Override
    public Author getEntityById(Integer id) {
        return new Author((AuthorHiber) dao.getEntityById(id));
    }
    @Override
    public void update(Author entity) {
        dao.update(entity.getEntity());
    }
    @Override
    public void delete(Author entity) {
        dao.delete(entity.getEntity());
    }
    @Override
    public int create(Author entity) {
        return dao.create(entity.getEntity());
    }
    @Override
    public List<Author> searchEntityByName(Author entity) {
        List<EntityBaseHiber> list = dao.searchEntityByName(entity.getEntity());
        List<Author> authors = new ArrayList<>();
        for (EntityBaseHiber ebh : list) {
            authors.add(new Author((AuthorHiber) ebh));
        }
        return authors;
    }
}
