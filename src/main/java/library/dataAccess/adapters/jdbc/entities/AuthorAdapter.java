package library.dataAccess.adapters.jdbc.entities;

import library.dataAccess.connectors.jdbc.entities.Author;

import java.util.List;

public class AuthorAdapter {

    private Author entity;

    public AuthorAdapter() {
        this.entity = new Author();
    }

    public AuthorAdapter(Author entity) {
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
        return entity.getBooksList();
    }
    public void setBooksList(List<BookAdapter> booksIdList) {
        entity.setBooksList(booksIdList);
    }
    @Override
    public String toString() {
        return entity.toString();
    }
}
