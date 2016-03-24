package library.dataAccess.connectors.jdbc.entities;

import library.dataAccess.adapters.jdbc.entities.Author;

import java.util.List;

public class BookJdbc {

    private int id;
    private String title;
    private int pubYear;
    private int genereId;
    private List<Author> authorsList;


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

    public List<Author> getAuthorsList() {
        return authorsList;
    }

    public void setAuthorsList(List<Author> authorsList) {
        this.authorsList = authorsList;
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
