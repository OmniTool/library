package library.dataAccess.connectors.jdbc.entities;

public class BookAuthorJdbc {

    private int id;
    private int bookId;
    private int authorId;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public int getAuthorId() {
        return authorId;
    }
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
    @Override
    public String toString() {
        return "BookAuthor{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", authorId=" + authorId +
                '}';
    }
}
