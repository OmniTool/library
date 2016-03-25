package library.dataAccess.adapters.jdbc.entities;

import library.dataAccess.connectors.jdbc.entities.Book;

import java.util.List;

public class BookAdapter {

    private Book entity;

    public BookAdapter() {
        this.entity = new Book();
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
        return entity.getGenereId();
    }
    public void setGenereId(int genereId) {
        entity.setGenereId(genereId);
    }
    public List<AuthorAdapter> getAuthorsList() {
        List<AuthorAdapter> list = entity.getAuthorsList();
        return list;
    }
    public void setAuthorsList(List<AuthorAdapter> authorsList) {
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
