package library.dataAccess.adapters.hibernate.dao.impl;

import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;
import library.dataAccess.connectors.hibernate.dao.impl.DAOAuthor;
import library.dataAccess.connectors.hibernate.entities.AuthorHiber;
import library.dataAccess.connectors.hibernate.entities.EntityBaseHiber;

import java.util.ArrayList;
import java.util.List;

public class DBManagerAuthor implements ManagerDAO<AuthorAdapter, Integer> {

    private DAOAuthor dao = new DAOAuthor();

    @Override
    public List<AuthorAdapter> getAll() {
        return dao.getAll();
    }
    @Override
    public AuthorAdapter getEntityById(Integer id) {
        return new AuthorAdapter((AuthorHiber) dao.getEntityById(id));
    }
    @Override
    public void update(AuthorAdapter entity) {
        dao.update(entity.getEntity());
    }
    @Override
    public void delete(AuthorAdapter entity) {
        dao.delete(entity.getEntity());
    }
    @Override
    public int create(AuthorAdapter entity) {
        return dao.create(entity.getEntity());
    }
    @Override
    public List<AuthorAdapter> searchEntityByName(AuthorAdapter entity) {
        List<EntityBaseHiber> list = dao.searchEntityByName(entity.getEntity());
        List<AuthorAdapter> authors = new ArrayList<>();
        for (EntityBaseHiber ebh : list) {
            authors.add(new AuthorAdapter((AuthorHiber) ebh));
        }
        return authors;
    }
}
