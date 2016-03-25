package library.dataAccess.adapters.hibernate.entities;

import library.dataAccess.connectors.hibernate.dao.BaseDAO;
import library.dataAccess.connectors.hibernate.dao.impl.DAOAuthor;
import library.dataAccess.connectors.hibernate.dao.impl.DAOBook;
import library.dataAccess.connectors.hibernate.entities.Author;
import library.dataAccess.connectors.hibernate.entities.BookAuthor;
import library.dataAccess.connectors.hibernate.entities.Book;


public class BookAuthorAdapter {

    private BookAuthor entity;

    public BookAuthorAdapter() {
        this.entity = new BookAuthor();
    }
    public BookAuthorAdapter(BookAuthor entity) {
        this.entity = entity;
    }

    public int getId() {
        return entity.getId();
    }
    public void setId(int id) {
        entity.setId(id);
    }
    public int getBookId() {
        return entity.getBook().getId();
    }
    public void setBookId(int bookId) {
        BaseDAO daoBook = new DAOBook();
        entity.setBook((Book)daoBook.getEntityById(bookId));
    }
    public int getAuthorId() {
        return entity.getAuthor().getId();
    }
    public void setAuthorId(int authorId) {
        BaseDAO daoAuthor = new DAOAuthor();
        entity.setAuthor((Author)daoAuthor.getEntityById(authorId));
    }
    @Override
    public String toString() {
        return entity.toString();
    }
    public BookAuthor getEntity() {
        return entity;
    }
    public void setEntity(BookAuthor entity) {
        this.entity = entity;
    }

}
