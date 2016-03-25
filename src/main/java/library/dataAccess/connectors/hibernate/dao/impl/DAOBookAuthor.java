package library.dataAccess.connectors.hibernate.dao.impl;

import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;
import library.dataAccess.connectors.hibernate.entities.BookAuthorHiber;
import library.dataAccess.connectors.hibernate.entities.EntityBaseHiber;
import library.dataAccess.connectors.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DAOBookAuthor extends BaseDAOImpl {
    public DAOBookAuthor() {
        super(BookAuthorHiber.class);
    }

    @Override
    public List<EntityBaseHiber> searchEntityByName(EntityBaseHiber entity) {
        BookAuthorHiber bookAuthor = (BookAuthorHiber) entity;
        Session session = null;
        List<EntityBaseHiber> entities = new ArrayList<>();
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
    public List<BookAdapter> searchBooksByAuthor(AuthorAdapter entity) {
        Session session = null;
        List<BookAuthorHiber> entities = new ArrayList<>();
        List<BookAdapter> books = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    " e WHERE e.author.id = :author_id")
                    .setParameter("author_id", entity.getId())
                    .list();
        } finally {
            HibernateUtil.close(session);
        }
        for (BookAuthorHiber bah : entities) {
            books.add(new BookAdapter(bah.getBook()));
        }
        return books;
    }
    public List<AuthorAdapter> searchAuthorsByBook(BookAdapter entity) {
        Session session = null;
        List<BookAuthorHiber> entities = new ArrayList<>();
        List<AuthorAdapter> authors = new ArrayList<>();
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
        for (BookAuthorHiber bah : entities) {
            authors.add(new AuthorAdapter(bah.getAuthor()));
        }
        return authors;
    }
}