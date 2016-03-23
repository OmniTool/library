package library.dataAccess.accessPoint.active.hibernate.entities;

import library.dataAccess.hibernate.dao.BaseDAO;
import library.dataAccess.hibernate.dao.impl.DAOGenre;
import library.dataAccess.hibernate.entities.AuthorHiber;
import library.dataAccess.hibernate.entities.BookAuthorHiber;
import library.dataAccess.hibernate.entities.BookHiber;
import library.dataAccess.hibernate.entities.GenreHiber;


import java.util.ArrayList;
import java.util.List;

public class Book implements EntityBase {

    private BookHiber entity;

    public Book() {
        this.entity = new BookHiber();
    }

    public Book(BookHiber entity) {
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
        entity.setGenre((GenreHiber)daoGenre.getEntityById(genereId));
    }

    public List<Author> getAuthorsList() {
        List<BookAuthorHiber> list = entity.getAuthorsList();
        List<Author> authorsList= new ArrayList<>();
        for (BookAuthorHiber bah : list)
            authorsList.add(new Author(bah.getAuthor()));
        return authorsList;
    }

    public void setAuthorsList(List<Author> authorsList) {
        List<BookAuthorHiber> list = new ArrayList<>();
        BaseDAO daoGenre = new DAOGenre();
        for (Author a : authorsList)
            //public AuthorHiber(String secondName, String firstName, String middleName, Integer birthYear, String biography, List<BookAuthorHiber> booksList)
            list.add(new BookAuthorHiber(entity, new AuthorHiber(a.getSecondName(), a.getFirstName(), a.getMiddleName(), a.getBirthYear(), a.getBiography(), null)));
        entity.setAuthorsList(list);
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

    public BookHiber getEntity() {
        return entity;
    }

    public void setEntity(BookHiber entity) {
        this.entity = entity;
    }
}
