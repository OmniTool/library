package library.dataAccess.adapters.hibernate.dao.impl;

import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;
import library.dataAccess.connectors.hibernate.dao.impl.DAOAuthor;
import library.dataAccess.connectors.hibernate.entities.Author;
import library.dataAccess.connectors.hibernate.entities.EntityBase;

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
        return new AuthorAdapter((Author) dao.getEntityById(id));
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
        List<EntityBase> list = dao.searchEntityByName(entity.getEntity());
        List<AuthorAdapter> authors = new ArrayList<>();
        for (EntityBase ebh : list) {
            authors.add(new AuthorAdapter((Author) ebh));
        }
        return authors;
    }
}
