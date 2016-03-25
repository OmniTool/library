package library.dataAccess.adapters.hibernate.dao.impl;

import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.Genre;
import library.dataAccess.connectors.hibernate.dao.impl.DAOGenre;
import library.dataAccess.connectors.hibernate.entities.EntityBaseHiber;
import library.dataAccess.connectors.hibernate.entities.GenreHiber;

import java.util.ArrayList;
import java.util.List;

public class DBManagerGenre implements ManagerDAO<Genre, Integer> {

    private DAOGenre dao = new DAOGenre();

    @Override
    public List<Genre> getAll() {
    return dao.getAll();
    }
    @Override
    public Genre getEntityById(Integer id) {
        return new Genre((GenreHiber) dao.getEntityById(id));
    }
    @Override
    public void update(Genre entity) {
        dao.update(entity.getEntity());
    }
    @Override
    public void delete(Genre entity) {
        dao.delete(entity.getEntity());
    }
    @Override
    public int create(Genre entity) {
        return dao.create(entity.getEntity());
    }
    @Override
    public List<Genre> searchEntityByName(Genre entity) {
        List<EntityBaseHiber> list = dao.searchEntityByName(entity.getEntity());
        List<Genre> books = new ArrayList<>();
        for (EntityBaseHiber ebh : list) {
            books.add(new Genre((GenreHiber) ebh));
        }
        return books;
    }
}
