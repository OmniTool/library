package library.hibernate.entities;

import javax.persistence.*;

@Entity
@Table(name = "books_authors", schema = "public", catalog = "library_test")
public class BookAuthor extends EntityBase {
    //private int id;
    Book book;
    Author author;

//    public BookAuthor() {
//    }

//    public BookAuthor(Book book, Author author) {
//        this.book = book;
//        this.author = author;
//    }

    //@Access(AccessType.PROPERTY)
    //@Access(AccessType.PROPERTY)

    @ManyToOne(targetEntity=Book.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id"//,
//            foreignKey = @ForeignKey(name = "")
    )

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @ManyToOne(targetEntity=Author.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id",
            foreignKey = @ForeignKey(name = "books_authors_author_id_fkey")
    )
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_authors_id")
//    //@SequenceGenerator( name = "books_authors_id", schema = "public", allocationSize = 1)
//    @SequenceGenerator(name = "books_authors_id", sequenceName = "books_authors_id", allocationSize = 1)
//    @Column(name = "id", insertable = false)
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookAuthor that = (BookAuthor) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
