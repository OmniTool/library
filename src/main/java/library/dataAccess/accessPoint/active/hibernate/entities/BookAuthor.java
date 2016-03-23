package library.dataAccess.accessPoint.active.hibernate.entities;

import library.dataAccess.hibernate.dao.BaseDAO;
import library.dataAccess.hibernate.dao.impl.DAOAuthor;
import library.dataAccess.hibernate.dao.impl.DAOBook;
import library.dataAccess.hibernate.entities.AuthorHiber;
import library.dataAccess.hibernate.entities.BookAuthorHiber;
import library.dataAccess.hibernate.entities.BookHiber;


public class BookAuthor implements EntityBase {

    private BookAuthorHiber entity;

    public BookAuthor() {
        this.entity = new BookAuthorHiber();
    }

    public BookAuthor(BookAuthorHiber entity) {
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
        entity.setBook((BookHiber)daoBook.getEntityById(bookId));
    }

    public int getAuthorId() {
        return entity.getAuthor().getId();
    }

    public void setAuthorId(int authorId) {
        BaseDAO daoAuthor = new DAOAuthor();
        entity.setAuthor((AuthorHiber)daoAuthor.getEntityById(authorId));
    }

    @Override
    public String toString() {
        return entity.toString();
    }

    public BookAuthorHiber getEntity() {
        return entity;
    }

    public void setEntity(BookAuthorHiber entity) {
        this.entity = entity;
    }
}
