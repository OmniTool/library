package library.hibernate.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genres", schema = "public", catalog = "library_test")
public class Genre extends EntityBase {
    //private int id;
    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "description")
    private String description;


    public Genre() {
    }

    public Genre(String title, String description) {
        this.title = title;
        this.description = description;
    }


    //    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = false)
//    @OneToMany(mappedBy="genre")
//    private List<Book> booksList = new ArrayList<>();
//
//    public List<Book> getBooksList() {
//        return booksList;
//    }
//
//    public void setBooksList(List<Book> booksList) {
//        this.booksList = booksList;
//    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genres_id")
//    //@SequenceGenerator( name = "genres_id", schema = "public", allocationSize = 1)
//    @SequenceGenerator(name = "genres_id", sequenceName = "genres_id", allocationSize = 1)
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
//    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genres = (Genre) o;

        if (id != genres.id) return false;
        if (title != null ? !title.equals(genres.title) : genres.title != null) return false;
        if (description != null ? !description.equals(genres.description) : genres.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "title='" + title + '\'' +
                '}';
    }
}
