package library.dataAccess.adapters.hibernate.entities;

import library.dataAccess.connectors.hibernate.dao.BaseDAO;
import library.dataAccess.connectors.hibernate.dao.impl.DAOAuthor;
import library.dataAccess.connectors.hibernate.entities.AuthorHiber;
import library.dataAccess.connectors.hibernate.entities.BookAuthorHiber;


import java.util.ArrayList;
import java.util.List;

public class AuthorAdapter {

    private AuthorHiber entity;

    public AuthorAdapter() {
        this.entity = new AuthorHiber();
    }
    public AuthorAdapter(int id) {
        BaseDAO dao = new DAOAuthor();
        this.entity = (AuthorHiber) dao.getEntityById(id);
    }
    public AuthorAdapter(AuthorHiber entity) {
        this.entity = entity;
    }

    public AuthorHiber getEntity() {
        return entity;
    }
    public void setEntity(AuthorHiber entity) {
        this.entity = entity;
    }
    public int getId() {
        return entity.getId();
    }
    public void setId(int id) {
        entity.setId(id);
    }
    public String getSecondName() {
        return entity.getSecondName();
    }
    public void setSecondName(String secondName) {
        entity.setSecondName(secondName);
    }
    public String getFirstName() {
        return entity.getFirstName();
    }
    public void setFirstName(String firstName) {
        entity.setFirstName(firstName);
    }
    public String getMiddleName() {
        return entity.getMiddleName();
    }
    public void setMiddleName(String middleName) {
        entity.setMiddleName(middleName);
    }
    public int getBirthYear() {
        return entity.getBirthYear();
    }
    public void setBirthYear(int birthYear) {
        entity.setBirthYear(birthYear);
    }
    public String getBiography() {
        return entity.getBiography();
    }
    public void setBiography(String biography) {
        entity.setBiography(biography);
    }
    public List<BookAdapter> getBooksList() {
        List<BookAuthorHiber> list = entity.getBooksList();
        List<BookAdapter> booksList= new ArrayList<>();
        for (BookAuthorHiber bah : list)
            booksList.add(new BookAdapter(bah.getBook()));
        return booksList;
    }
    public void setBooksList(List<BookAdapter> booksList) {
        entity.getBooksList().clear();
        for (BookAdapter e : booksList)
            entity.getBooksList().add(new BookAuthorHiber(e.getEntity(), entity));
    }
    @Override
    public String toString() {
        return entity.toString();
    }
}
