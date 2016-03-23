package library.dataAccess.accessPoint.entities;

import library.dataAccess.jdbc.entities.AuthorJdbc;

import java.util.List;

public class Author {

    private AuthorJdbc entity;

    public Author() {
        this.entity = new AuthorJdbc();
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

    public List<Book> getBooksList() {
        return entity.getBooksList();
    }

    public void setBooksList(List<Book> booksIdList) {
        entity.setBooksList(booksIdList);
    }

    @Override
    public String toString() {
        return entity.toString();
    }
}
