package library.dataAccess.adapters.jdbc.entities;

import library.dataAccess.connectors.jdbc.entities.BookAuthorJdbc;

public class BookAuthor {

    private BookAuthorJdbc entity;

    public BookAuthor() {
        this.entity = new BookAuthorJdbc();
    }

    public int getId() {
        return entity.getId();
    }

    public void setId(int id) {
        entity.setId(id);
    }

    public int getBookId() {
        return entity.getBookId();
    }

    public void setBookId(int bookId) {
        entity.setBookId(bookId);
    }

    public int getAuthorId() {
        return entity.getAuthorId();
    }

    public void setAuthorId(int authorId) {
        entity.setAuthorId(authorId);
    }

    @Override
    public String toString() {
        return entity.toString();
    }
}
