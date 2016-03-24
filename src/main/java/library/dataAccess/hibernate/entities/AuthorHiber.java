package library.dataAccess.hibernate.entities;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors", schema = "public", catalog = "library_test")
public class AuthorHiber extends EntityBaseHiber {
    //private int id;
    @Basic
    @Column(name = "second_name")
    private String secondName;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "middle_name")
    private String middleName;

    @Basic
    @Column(name = "birth_year")
    private Integer birthYear;

    @Basic
    @Column(name = "biography")
    private String biography;

    @OneToMany(targetEntity=BookAuthorHiber.class, mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)

    private List<BookAuthorHiber> booksList = new ArrayList<>();


    public AuthorHiber() {
    }

    public AuthorHiber(String secondName, String firstName, String middleName, Integer birthYear, String biography, List<BookAuthorHiber> booksList) {
        this.secondName = secondName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthYear = birthYear;
        this.biography = biography;
        this.booksList = booksList;
    }


//    @OneToMany(targetEntity=BookAuthor.class, mappedBy = "author", fetch = FetchType.EAGER/*, cascade = CascadeType.ALL, orphanRemoval = true*/)
    public List<BookAuthorHiber> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<BookAuthorHiber> booksList) {
        this.booksList = booksList;
    }

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_id")
//    @SequenceGenerator(name = "authors_id", sequenceName = "authors_id", allocationSize = 1)
//    //@SequenceGenerator( name = "authors_id", schema = "public", allocationSize = 1)
//    @Column(name = "id", insertable = false)
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

//    @Basic
//    @Column(name = "second_name")
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

//    @Basic
//    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

//    @Basic
//    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

//    @Basic
//    @Column(name = "birth_year")
    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

//    @Basic
//    @Column(name = "biography")
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorHiber authors = (AuthorHiber) o;

        //if (id != authors.id) return false;
        if (secondName != null ? !secondName.equals(authors.secondName) : authors.secondName != null) return false;
        if (firstName != null ? !firstName.equals(authors.firstName) : authors.firstName != null) return false;
        if (middleName != null ? !middleName.equals(authors.middleName) : authors.middleName != null) return false;
        if (birthYear != null ? !birthYear.equals(authors.birthYear) : authors.birthYear != null) return false;
        if (biography != null ? !biography.equals(authors.biography) : authors.biography != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        //int result = id;
        int result = 31;
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (birthYear != null ? birthYear.hashCode() : 0);
        result = 31 * result + (biography != null ? biography.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AuthorHiber{" +
                "id='" + id + '\'' +
                "secondName='" + secondName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthYear=" + birthYear +
                ", biography='" + biography + '\'' +
                '}';
    }
}
