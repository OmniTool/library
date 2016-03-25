package library.dataAccess.connectors.hibernate.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books", schema = "public", catalog = "library_test")
public class BookHiber extends EntityBaseHiber {

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

    public BookHiber() {}

    public List<BookAuthorHiber> getAuthorsList() {
        return authorsList;
    }
    public void setAuthorsList(List<BookAuthorHiber> authorsList) {
        this.authorsList = authorsList;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getPubYear() {
        return pubYear;
    }
    public void setPubYear(Integer pubYear) {
        this.pubYear = pubYear;
    }
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
        if (title != null ? !title.equals(books.title) : books.title != null) return false;
        if (pubYear != null ? !pubYear.equals(books.pubYear) : books.pubYear != null) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (pubYear != null ? pubYear.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "BookHiber{" +
                "id='" + id + '\'' +
                "title='" + title + '\'' +
                ", pubYear=" + pubYear +
                ", genre=" + genre +
                '}';
    }
}
