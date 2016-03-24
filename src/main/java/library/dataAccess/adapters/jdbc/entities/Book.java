package library.dataAccess.adapters.jdbc.entities;

import library.dataAccess.connectors.jdbc.entities.BookJdbc;

import java.util.List;

public class Book {

    private BookJdbc entity;

    public Book() {
        this.entity = new BookJdbc();
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
        return entity.getGenereId();
    }
    public void setGenereId(int genereId) {
        entity.setGenereId(genereId);
    }
    public List<Author> getAuthorsList() {
        List<Author> list = entity.getAuthorsList();
        return list;
    }
    public void setAuthorsList(List<Author> authorsList) {
        entity.setAuthorsList(authorsList);
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
}
