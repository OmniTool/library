package library.dataAccess.connectors.hibernate.entities;

import javax.persistence.*;

@Entity
@Table(name = "books_authors", schema = "public", catalog = "library_test")
public class BookAuthorHiber extends EntityBaseHiber {
    @ManyToOne(targetEntity=BookHiber.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    BookHiber book;
    @ManyToOne(targetEntity=AuthorHiber.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id",
            foreignKey = @ForeignKey(name = "books_authors_author_id_fkey")
    )
    AuthorHiber author;

    public BookAuthorHiber() {}
    public BookAuthorHiber(BookHiber book, AuthorHiber author) {
        this.book = book;
        this.author = author;
    }
    public BookHiber getBook() {
        return book;
    }
    public void setBook(BookHiber book) {
        this.book = book;
    }
    public AuthorHiber getAuthor() {
        return author;
    }
    public void setAuthor(AuthorHiber author) {
        this.author = author;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookAuthorHiber that = (BookAuthorHiber) o;
        if (book != null ? !book.equals(that.book) : that.book != null) return false;
        return !(author != null ? !author.equals(that.author) : that.author != null);
    }
    @Override
    public int hashCode() {
        int result = book != null ? book.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "BookAuthor{" +
                "id='" + id + '\'' +
                "book=" + book +
                ", author=" + author +
                '}';
    }
}
