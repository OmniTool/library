package library.dataAccess.adapters.hibernate.entities;

import library.dataAccess.connectors.hibernate.dao.BaseDAO;
import library.dataAccess.connectors.hibernate.dao.impl.DAOAuthor;
import library.dataAccess.connectors.hibernate.entities.Author;
import library.dataAccess.connectors.hibernate.entities.BookAuthor;


import java.util.ArrayList;
import java.util.List;

public class AuthorAdapter {

    private Author entity;

    public AuthorAdapter() {
        this.entity = new Author();
    }
    public AuthorAdapter(int id) {
        BaseDAO dao = new DAOAuthor();
        this.entity = (Author) dao.getEntityById(id);
    }
    public AuthorAdapter(Author entity) {
        this.entity = entity;
    }

    public Author getEntity() {
        return entity;
    }
    public void setEntity(Author entity) {
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
        List<BookAuthor> list = entity.getBooksList();
        List<BookAdapter> booksList= new ArrayList<>();
        for (BookAuthor bookAuthor : list)
            booksList.add(new BookAdapter(bookAuthor.getBook()));
        return booksList;
    }
    public void setBooksList(List<BookAdapter> booksList) {
        entity.getBooksList().clear();
        for (BookAdapter e : booksList)
            entity.getBooksList().add(new BookAuthor(e.getEntity(), entity));
    }
    @Override
    public String toString() {
        return entity.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorAdapter that = (AuthorAdapter) o;
        return !(entity != null ? !entity.equals(that.entity) : that.entity != null);
    }

    @Override
    public int hashCode() {
        return entity != null ? entity.hashCode() : 0;
    }
}
