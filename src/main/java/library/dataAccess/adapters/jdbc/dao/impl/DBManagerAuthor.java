package library.dataAccess.adapters.jdbc.dao.impl;


import library.dataAccess.adapters.jdbc.entities.Author;
import library.dataAccess.connectors.jdbc.dao.impl.JDBCManagerAuthor;
import library.dataAccess.accessPoint.ManagerDAO;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class DBManagerAuthor implements ManagerDAO<Author, Integer> {

    private JDBCManagerAuthor dao = new JDBCManagerAuthor();

    @Override
    public List<Author> getAll() throws SQLException, NamingException {
        return dao.getAll();
    }
    @Override
    public Author getEntityById(Integer id) throws SQLException, NamingException {
        return dao.getEntityById(id);
    }
    @Override
    public void update(Author entity) throws SQLException, NamingException {
        dao.update(entity);
    }
    @Override
    public void delete(Author entity) throws SQLException, NamingException {
        dao.delete(entity);
    }
    @Override
    public int create(Author entity) throws SQLException, NamingException {
        return dao.create(entity);
    }
    @Override
    public List<Author> searchEntityByName(Author entity) throws SQLException, NamingException {
        return dao.searchEntityByName(entity);
    }
}
