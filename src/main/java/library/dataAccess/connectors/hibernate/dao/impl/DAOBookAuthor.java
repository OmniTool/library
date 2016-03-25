package library.dataAccess.connectors.hibernate.dao.impl;

import library.dataAccess.connectors.hibernate.entities.*;
import library.dataAccess.connectors.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DAOBookAuthor extends BaseDAOImpl {
    public DAOBookAuthor() {
        super(BookAuthor.class);
    }

    @Override
    public List<EntityBase> searchEntityByName(EntityBase entity) {
        BookAuthor bookAuthor = (BookAuthor) entity;
        Session session = null;
        List<EntityBase> entities = new ArrayList<>();
        if (entity == null) return entities;
        try {
            session = HibernateUtil.getSession();
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    " e WHERE e.author.id = :author_id AND e.book.id = :book_id")
                    .setParameter("author_id", bookAuthor.getAuthor().getId())
                    .setParameter("book_id", bookAuthor.getBook().getId())
                    .list();
        } finally {HibernateUtil.close(session);}
        return entities;
    }
    public List<Book> searchBooksByAuthor(Author entity) {
        Session session = null;
        List<BookAuthor> entities = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        if (entity == null) return books;
        try {
            session = HibernateUtil.getSession();
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    " e WHERE e.author.id = :author_id")
                    .setParameter("author_id", entity.getId())
                    .list();
        } finally {
            HibernateUtil.close(session);
        }
        for (BookAuthor bookAuthor : entities) {
            books.add(bookAuthor.getBook());
        }
        return books;
    }
    public List<Author> searchAuthorsByBook(Book entity) {
        Session session = null;
        List<BookAuthor> entities = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        if (entity == null) return authors;
        try {
            session = HibernateUtil.getSession();
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    " e WHERE e.book.id = :book_id")
                    .setParameter("book_id", entity.getId())
                    .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        for (BookAuthor bookAuthor : entities) {
            authors.add(bookAuthor.getAuthor());
        }
        return authors;
    }
}