package library.dataAccess.hibernate.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books", schema = "public", catalog = "library_test")
public class BookHiber extends EntityBaseHiber {
    //private int id;
    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "pub_year")
    private Integer pubYear;

    @ManyToOne(targetEntity = GenreHiber.class)
    @JoinColumn(name = "genere_id",
            foreignKey = @ForeignKey(name = "books_genre_id_fkey")
    )
    private GenreHiber genre;

    @OneToMany(targetEntity=BookAuthorHiber.class, mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookAuthorHiber> authorsList = new ArrayList<>();


    public BookHiber() {
    }

    public BookHiber(String title, Integer pubYear, GenreHiber genre, List<BookAuthorHiber> authorsList) {
        this.title = title;
        this.pubYear = pubYear;
        this.genre = genre;
        this.authorsList = authorsList;
    }


//    @OneToMany(targetEntity=BookAuthor.class, mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<BookAuthorHiber> getAuthorsList() {
        return authorsList;
    }

    public void setAuthorsList(List<BookAuthorHiber> authorsList) {
        this.authorsList = authorsList;
    }


//    @ManyToMany(mappedBy = "booksList")
//    private List<Author> authorsList = new ArrayList<>();
//
//    public List<Author> getAuthorsList() {
//        return authorsList;
//    }
//
//    public void setAuthorsList(List<Author> authorsList) {
//        this.authorsList = authorsList;
//    }


//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_id")
//    //@SequenceGenerator( name = "books_id", schema = "public", allocationSize = 1)
//    @SequenceGenerator(name = "books_id", sequenceName = "books_id", allocationSize = 1)
//    @Column(name = "id", insertable = false)
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

//    @Basic
//    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    @Basic
//    @Column(name = "pub_year")
    public Integer getPubYear() {
        return pubYear;
    }

    public void setPubYear(Integer pubYear) {
        this.pubYear = pubYear;
    }

//    @ManyToOne
//    @JoinColumn(name = "genre_id",
//            foreignKey = @ForeignKey(name = "books_genre_id_fkey")
//    )
    //@MapsId
    //@PrimaryKeyJoinColumn

//    @ManyToOne(targetEntity = Genre.class)
//    @JoinColumn(name = "genere_id",
//            foreignKey = @ForeignKey(name = "books_genre_id_fkey")
//    )
    public GenreHiber getGenre() {
        return genre;
    }

    public void setGenre(GenreHiber genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookHiber books = (BookHiber) o;

        if (id != books.id) return false;
        if (title != null ? !title.equals(books.title) : books.title != null) return false;
        if (pubYear != null ? !pubYear.equals(books.pubYear) : books.pubYear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (pubYear != null ? pubYear.hashCode() : 0);
        return result;
    }
}
