package library.dataAccess.accessPoint;

//hibernate
import library.dataAccess.adapters.hibernate.dao.impl.*;
import library.dataAccess.adapters.hibernate.entities.*;

////jdbc
//import library.dataAccess.adapters.jdbc.dao.impl.*;
//import library.dataAccess.adapters.jdbc.entities.*;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class DAO {

    private static DBManagerGenre daoGenre = new DBManagerGenre();
    private static DBManagerAuthor daoAuthor = new DBManagerAuthor();
    private static DBManagerBook daoBook = new DBManagerBook();

    public List<GenreAdapter> getAllGenre() throws SQLException, NamingException { return daoGenre.getAll(); }
    public List<AuthorAdapter> getAllAuthor() throws SQLException, NamingException { return daoAuthor.getAll(); }
    public List<BookAdapter> getAllBook() throws SQLException, NamingException { return daoBook.getAll(); }

    public GenreAdapter getEntityByIdGenre(int id) throws SQLException, NamingException { return daoGenre.getEntityById(id); }
    public AuthorAdapter getEntityByIdAuthor(int id) throws SQLException, NamingException { return daoAuthor.getEntityById(id); }
    public BookAdapter getEntityByIdBook(int id) throws SQLException, NamingException { return daoBook.getEntityById(id); }

    public void update(GenreAdapter entity) throws SQLException, NamingException { daoGenre.update(entity); }
    public void update(AuthorAdapter entity) throws SQLException, NamingException { daoAuthor.update(entity); }
    public void update(BookAdapter entity) throws SQLException, NamingException { daoBook.update(entity); }

    public void delete(GenreAdapter entity) throws SQLException, NamingException { daoGenre.delete(entity); }
    public void delete(AuthorAdapter entity) throws SQLException, NamingException { daoAuthor.delete(entity); }
    public void delete(BookAdapter entity) throws SQLException, NamingException { daoBook.delete(entity); }

    public int create(GenreAdapter entity) throws SQLException, NamingException { return daoGenre.create(entity); }
    public int create(AuthorAdapter entity) throws SQLException, NamingException { return daoAuthor.create(entity); }
    public int create(BookAdapter entity) throws SQLException, NamingException { return daoBook.create(entity); }

    public List<GenreAdapter> searchEntityByName(GenreAdapter entity) throws SQLException, NamingException { return daoGenre.searchEntityByName(entity); }
    public List<AuthorAdapter> searchEntityByName(AuthorAdapter entity) throws SQLException, NamingException { return daoAuthor.searchEntityByName(entity); }
    public List<BookAdapter> searchEntityByName(BookAdapter entity) throws SQLException, NamingException { return daoBook.searchEntityByName(entity); }
}
