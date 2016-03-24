package library.dataAccess.accessPoint.active.entities;

import library.dataAccess.hibernate.dao.BaseDAO;
import library.dataAccess.hibernate.dao.impl.DAOAuthor;
import library.dataAccess.hibernate.dao.impl.DAOBook;
import library.dataAccess.hibernate.dao.impl.DAOGenre;
import library.dataAccess.hibernate.entities.AuthorHiber;
import library.dataAccess.hibernate.entities.BookAuthorHiber;
import library.dataAccess.hibernate.entities.BookHiber;
import library.dataAccess.hibernate.entities.GenreHiber;


import java.util.ArrayList;
import java.util.List;

public class Author implements EntityBase {

    private AuthorHiber entity;

    public Author() {
        this.entity = new AuthorHiber();
    }

    public Author(int id) {
        BaseDAO dao = new DAOAuthor();
        this.entity = (AuthorHiber) dao.getEntityById(id);
    }

    public Author(AuthorHiber entity) {
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

    public List<Book> getBooksList() {
        List<BookAuthorHiber> list = entity.getBooksList();
        List<Book> booksList= new ArrayList<>();
        for (BookAuthorHiber bah : list)
            booksList.add(new Book(bah.getBook()));
        return booksList;
    }

    public void setBooksList(List<Book> booksList) {
//        List<BookAuthorHiber> list = new ArrayList<>();
//        //BaseDAO daoBook = new DAOBook();
//        for (Book b : booksList)
//            list.add(new BookAuthorHiber(b.getEntity(), entity));
//        entity.setBooksList(list);
        entity.getBooksList().clear();
        for (Book b : booksList)
            entity.getBooksList().add(new BookAuthorHiber(b.getEntity(), entity));
    }

    @Override
    public String toString() {
        return entity.toString();
    }
}
