package library.dataAccess.accessPoint;

//hibernate
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerAuthor;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerGenre;
import library.dataAccess.adapters.hibernate.entities.Author;
import library.dataAccess.adapters.hibernate.entities.Book;
import library.dataAccess.adapters.hibernate.entities.Genre;

////jdbc
//import library.dataAccess.adapters.jdbc.dao.impl.DBManagerAuthor;
//import library.dataAccess.adapters.jdbc.dao.impl.DBManagerBook;
//import library.dataAccess.adapters.jdbc.dao.impl.DBManagerGenre;
//import library.dataAccess.adapters.jdbc.entities.Author;
//import library.dataAccess.adapters.jdbc.entities.Book;
//import library.dataAccess.adapters.jdbc.entities.Genre;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class DAO {

    private static DBManagerGenre daoGenre = new DBManagerGenre();
    private static DBManagerAuthor daoAuthor = new DBManagerAuthor();
    private static DBManagerBook daoBook = new DBManagerBook();
    

    public List<Genre> getAllGenre() throws SQLException, NamingException {

        return daoGenre.getAll();
    }
    public List<Author> getAllAuthor() throws SQLException, NamingException {

        return daoAuthor.getAll();
    }
    public List<Book> getAllBook() throws SQLException, NamingException {

        return daoBook.getAll();
    }


    public Genre getEntityByIdGenre(int id) throws SQLException, NamingException {

        return daoGenre.getEntityById(id);
    }
    public Author getEntityByIdAuthor(int id) throws SQLException, NamingException {

        return daoAuthor.getEntityById(id);
    }
    public Book getEntityByIdBook(int id) throws SQLException, NamingException {

        return daoBook.getEntityById(id);
    }



    public void update(Genre entity) throws SQLException, NamingException {
        daoGenre.update(entity);

    }
    public void update(Author entity) throws SQLException, NamingException {
        daoAuthor.update(entity);

    }
    public void update(Book entity) throws SQLException, NamingException {
        daoBook.update(entity);

    }


    public void delete(Genre entity) throws SQLException, NamingException {
        daoGenre.delete(entity);

    }
    public void delete(Author entity) throws SQLException, NamingException {
        daoAuthor.delete(entity);

    }
    public void delete(Book entity) throws SQLException, NamingException {
        daoBook.delete(entity);

    }


    public int create(Genre entity) throws SQLException, NamingException {

        return daoGenre.create(entity);
    }
    public int create(Author entity) throws SQLException, NamingException {

        return daoAuthor.create(entity);
    }
    public int create(Book entity) throws SQLException, NamingException {

        return daoBook.create(entity);
    }


    public List<Genre> searchEntityByName(Genre entity) throws SQLException, NamingException {

        return daoGenre.searchEntityByName(entity);
    }
    public List<Author> searchEntityByName(Author entity) throws SQLException, NamingException {

        return daoAuthor.searchEntityByName(entity);
    }
    public List<Book> searchEntityByName(Book entity) throws SQLException, NamingException {

        return daoBook.searchEntityByName(entity);
    }
}
