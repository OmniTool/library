package library.dataAccess.connectors.hibernate.entities;

import javax.persistence.*;

@Entity
@Table(name = "genres", schema = "public", catalog = "library_test")
public class GenreHiber extends EntityBaseHiber {
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "description")
    private String description;

    public GenreHiber() {}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
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
        GenreHiber genres = (GenreHiber) o;
        if (title != null ? !title.equals(genres.title) : genres.title != null) return false;
        if (description != null ? !description.equals(genres.description) : genres.description != null) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "GenreHiber{" +
                "id='" + id + '\'' +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
