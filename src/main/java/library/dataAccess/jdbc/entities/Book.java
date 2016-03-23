package library.dataAccess.jdbc.entities;

import java.util.List;

public class Book extends EntityBase {

    private int id;
    private String title;
    private int pubYear;
    private int genereId;
    private List<library.dataAccess.jdbc.entities.Author> authorsList;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPubYear() {
        return pubYear;
    }

    public void setPubYear(int pubYear) {
        this.pubYear = pubYear;
    }

    public int getGenereId() {
        return genereId;
    }

    public void setGenereId(int genereId) {
        this.genereId = genereId;
    }

    public List<library.dataAccess.jdbc.entities.Author> getAuthorsList() {
        return authorsList;
    }

    public void setAuthorsList(List<library.dataAccess.jdbc.entities.Author> authorsIdList) {
        this.authorsList = authorsIdList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pubYear=" + pubYear +
                ", genereId=" + genereId +
                ", authorsIdList=" + authorsList +
                '}';
    }
}
