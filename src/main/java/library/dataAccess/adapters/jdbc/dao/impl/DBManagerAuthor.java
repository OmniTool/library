package library.dataAccess.adapters.jdbc.dao.impl;


import library.dataAccess.adapters.jdbc.entities.AuthorAdapter;
import library.dataAccess.connectors.jdbc.dao.impl.DAOAuthor;
import library.dataAccess.accessPoint.ManagerDAO;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class DBManagerAuthor implements ManagerDAO<AuthorAdapter, Integer> {

    private DAOAuthor dao = new DAOAuthor();

    @Override
    public List<AuthorAdapter> getAll() throws SQLException, NamingException {
        return dao.getAll();
    }
    @Override
    public AuthorAdapter getEntityById(Integer id) throws SQLException, NamingException {
        return dao.getEntityById(id);
    }
    @Override
    public void update(AuthorAdapter entity) throws SQLException, NamingException {
        dao.update(entity);
    }
    @Override
    public void delete(AuthorAdapter entity) throws SQLException, NamingException {
        dao.delete(entity);
    }
    @Override
    public int create(AuthorAdapter entity) throws SQLException, NamingException {
        return dao.create(entity);
    }
    @Override
    public List<AuthorAdapter> searchEntityByName(AuthorAdapter entity) throws SQLException, NamingException {
        return dao.searchEntityByName(entity);
    }
}
