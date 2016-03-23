package library.dataAccess.accessPoint.active.hibernate.dao.impl;

import library.dataAccess.accessPoint.active.hibernate.dao.ManagerDAO;
import library.dataAccess.accessPoint.active.hibernate.entities.Author;
import library.dataAccess.hibernate.dao.impl.DAOAuthor;
import library.dataAccess.hibernate.entities.AuthorHiber;
import library.dataAccess.hibernate.entities.EntityBaseHiber;
import library.dataAccess.jdbc.dao.impl.JDBCManagerAuthor;
import javax.naming.NamingException;
import java.sql.SQLException;
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
