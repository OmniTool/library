package library.hibernate.entities;

import javax.persistence.*;

@Entity
@Table(name = "books", schema = "public", catalog = "library_test")
public class Book extends EntityBase {
    private int id;
    private String title;
    private Integer pubYear;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "pub_year")
    public Integer getPubYear() {
        return pubYear;
    }

    public void setPubYear(Integer pubYear) {
        this.pubYear = pubYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book books = (Book) o;

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
