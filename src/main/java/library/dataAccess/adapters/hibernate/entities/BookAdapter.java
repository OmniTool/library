package library.dataAccess.adapters.hibernate.entities;

import library.dataAccess.connectors.hibernate.dao.BaseDAO;
import library.dataAccess.connectors.hibernate.dao.impl.DAOBook;
import library.dataAccess.connectors.hibernate.dao.impl.DAOGenre;
import library.dataAccess.connectors.hibernate.entities.BookAuthor;
import library.dataAccess.connectors.hibernate.entities.Book;
import library.dataAccess.connectors.hibernate.entities.Genre;


import java.util.ArrayList;
import java.util.List;

public class BookAdapter {

    private Book entity;

    public BookAdapter() {
        this.entity = new Book();
    }
    public BookAdapter(int id) {
        BaseDAO dao = new DAOBook();
        this.entity = (Book) dao.getEntityById(id);
    }
    public BookAdapter(Book entity) {
        this.entity = entity;
    }

    public String getTitle() {
        return entity.getTitle();
    }
    public void setTitle(String title) {
        entity.setTitle(title);
    }
    public int getPubYear() {
        return entity.getPubYear();
    }
    public void setPubYear(int pubYear) {
        entity.setPubYear(pubYear);
    }
    public int getGenereId() {
        return entity.getGenre().getId();
    }
    public void setGenereId(int genereId) {
        BaseDAO daoGenre = new DAOGenre();
        entity.setGenre((Genre)daoGenre.getEntityById(genereId));
    }
    public List<AuthorAdapter> getAuthorsList() {
        List<BookAuthor> list = entity.getAuthorsList();
        List<AuthorAdapter> authorsList= new ArrayList<>();
        for (BookAuthor bookAuthor : list)
            authorsList.add(new AuthorAdapter(bookAuthor.getAuthor()));
        return authorsList;
    }
    public void setAuthorsList(List<AuthorAdapter> authorsList) {
        entity.getAuthorsList().clear();
        for (AuthorAdapter e : authorsList)
            entity.getAuthorsList().add(new BookAuthor(entity, e.getEntity()));
    }
    public int getId() {
        return entity.getId();
    }
    public void setId(int id) {
        entity.setId(id);
    }
    @Override
    public String toString() {
        return entity.toString();
    }
    public Book getEntity() {
        return entity;
    }
    public void setEntity(Book entity) {
        this.entity = entity;
    }
}
