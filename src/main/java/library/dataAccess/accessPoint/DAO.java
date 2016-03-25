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

    public List<GenreAdapter> getAllGenre() { return daoGenre.getAll(); }
    public List<AuthorAdapter> getAllAuthor() { return daoAuthor.getAll(); }
    public List<BookAdapter> getAllBook() { return daoBook.getAll(); }

    public GenreAdapter getEntityByIdGenre(int id) { return daoGenre.getEntityById(id); }
    public AuthorAdapter getEntityByIdAuthor(int id) { return daoAuthor.getEntityById(id); }
    public BookAdapter getEntityByIdBook(int id) { return daoBook.getEntityById(id); }

    public void update(GenreAdapter entity) { daoGenre.update(entity); }
    public void update(AuthorAdapter entity) { daoAuthor.update(entity); }
    public void update(BookAdapter entity) { daoBook.update(entity); }

    public void delete(GenreAdapter entity) { daoGenre.delete(entity); }
    public void delete(AuthorAdapter entity) { daoAuthor.delete(entity); }
    public void delete(BookAdapter entity) { daoBook.delete(entity); }

    public int create(GenreAdapter entity) { return daoGenre.create(entity); }
    public int create(AuthorAdapter entity) { return daoAuthor.create(entity); }
    public int create(BookAdapter entity) { return daoBook.create(entity); }

    public List<GenreAdapter> searchEntityByName(GenreAdapter entity) { return daoGenre.searchEntityByName(entity); }
    public List<AuthorAdapter> searchEntityByName(AuthorAdapter entity) { return daoAuthor.searchEntityByName(entity); }
    public List<BookAdapter> searchEntityByName(BookAdapter entity) { return daoBook.searchEntityByName(entity); }
}
