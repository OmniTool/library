package library.dataAccess.accessPoint;

import library.dataAccess.adapters.hibernate.dao.impl.DBManagerAuthor;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerGenre;
import library.dataAccess.adapters.hibernate.entities.Author;
import library.dataAccess.adapters.hibernate.entities.Book;
import library.dataAccess.adapters.hibernate.entities.Genre;

import java.util.List;

public class DAO {

    private static DBManagerGenre daoGenre = new DBManagerGenre();
    private static DBManagerAuthor daoAuthor = new DBManagerAuthor();
    private static DBManagerBook daoBook = new DBManagerBook();
    

    public List<Genre> getAllGenre() {

        return daoGenre.getAll();
    }
    public List<Author> getAllAuthor() {

        return daoAuthor.getAll();
    }
    public List<Book> getAllBook() {

        return daoBook.getAll();
    }


    public Genre getEntityByIdGenre(int id) {

        return daoGenre.getEntityById(id);
    }
    public Author getEntityByIdAuthor(int id) {

        return daoAuthor.getEntityById(id);
    }
    public Book getEntityByIdBook(int id) {

        return daoBook.getEntityById(id);
    }



    public void update(Genre entity) {
        daoGenre.update(entity);

    }
    public void update(Author entity) {
        daoAuthor.update(entity);

    }
    public void update(Book entity) {
        daoBook.update(entity);

    }


    public void delete(Genre entity) {
        daoGenre.delete(entity);

    }
    public void delete(Author entity) {
        daoAuthor.delete(entity);

    }
    public void delete(Book entity) {
        daoBook.delete(entity);

    }


    public int create(Genre entity) {

        return daoGenre.create(entity);
    }
    public int create(Author entity) {

        return daoAuthor.create(entity);
    }
    public int create(Book entity) {

        return daoBook.create(entity);
    }


    public List<Genre> searchEntityByName(Genre entity) {

        return daoGenre.searchEntityByName(entity);
    }
    public List<Author> searchEntityByName(Author entity) {

        return daoAuthor.searchEntityByName(entity);
    }
    public List<Book> searchEntityByName(Book entity) {

        return daoBook.searchEntityByName(entity);
    }
}
