package library.dataAccess.hibernate.dao.impl;

import library.dataAccess.accessPoint.active.hibernate.entities.Author;
import library.dataAccess.accessPoint.active.hibernate.entities.Book;
import library.dataAccess.hibernate.entities.AuthorHiber;
import library.dataAccess.hibernate.entities.BookAuthorHiber;
import library.dataAccess.hibernate.entities.BookHiber;
import library.dataAccess.hibernate.entities.EntityBaseHiber;
import library.dataAccess.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
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
            session = HibernateUtil.getSessionFactory().openSession();
            //FROM books_authors WHERE author_id = ? AND book_id = ?
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    " e WHERE e.author.id = :author_id AND e.book.id = :book_id")
                    .setParameter("author_id", bookAuthor.getAuthor().getId())
                    .setParameter("book_id", bookAuthor.getBook().getId())
                    .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return entities;
    }

    public List<Book> searchBooksByAuthor(Author entity) {
        Session session = null;
        List<BookAuthorHiber> entities = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    " e WHERE e.author.id = :author_id")
                    .setParameter("author_id", entity.getId())
                    .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        for (BookAuthorHiber bah : entities) {
            books.add(new Book(bah.getBook()));
        }
        return books;
    }

    public List<Author> searchAuthorsByBook(Book entity) {
        Session session = null;
        List<BookAuthorHiber> entities = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            entities = session.createQuery("FROM " + type.getSimpleName() +
                    " e WHERE e.book.id = :book_id")
                    .setParameter("book_id", entity.getId())
                    .list();
//            entities = session.createSQLQuery("SELECT * FROM books_authors WHERE book_id = :book_id")
//                    .setParameter("book_id", entity.getId())
//                    .list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        for (BookAuthorHiber bah : entities) {
            authors.add(new Author(bah.getAuthor()));
        }
        return authors;
    }
}