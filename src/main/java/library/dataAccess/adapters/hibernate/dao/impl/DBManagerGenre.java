package library.dataAccess.adapters.hibernate.dao.impl;

import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.GenreAdapter;
import library.dataAccess.connectors.hibernate.dao.impl.DAOGenre;
import library.dataAccess.connectors.hibernate.entities.EntityBaseHiber;
import library.dataAccess.connectors.hibernate.entities.GenreHiber;

import java.util.ArrayList;
import java.util.List;

public class DBManagerGenre implements ManagerDAO<GenreAdapter, Integer> {

    private DAOGenre dao = new DAOGenre();

    @Override
    public List<GenreAdapter> getAll() {
    return dao.getAll();
    }
    @Override
    public GenreAdapter getEntityById(Integer id) {
        return new GenreAdapter((GenreHiber) dao.getEntityById(id));
    }
    @Override
    public void update(GenreAdapter entity) {
        dao.update(entity.getEntity());
    }
    @Override
    public void delete(GenreAdapter entity) {
        dao.delete(entity.getEntity());
    }
    @Override
    public int create(GenreAdapter entity) {
        return dao.create(entity.getEntity());
    }
    @Override
    public List<GenreAdapter> searchEntityByName(GenreAdapter entity) {
        List<EntityBaseHiber> list = dao.searchEntityByName(entity.getEntity());
        List<GenreAdapter> books = new ArrayList<>();
        for (EntityBaseHiber ebh : list) {
            books.add(new GenreAdapter((GenreHiber) ebh));
        }
        return books;
    }
}
