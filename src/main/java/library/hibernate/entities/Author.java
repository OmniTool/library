package library.hibernate.entities;

import javax.persistence.*;

@Entity
@Table(name = "authors", schema = "public", catalog = "library_test")
public class Author extends EntityBase{
    private int id;
    private String secondName;
    private String firstName;
    private String middleName;
    private Integer birthYear;
    private String biography;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "second_name")
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "birth_year")
    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    @Basic
    @Column(name = "biography")
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

        Author authors = (Author) o;

        if (id != authors.id) return false;
        if (secondName != null ? !secondName.equals(authors.secondName) : authors.secondName != null) return false;
        if (firstName != null ? !firstName.equals(authors.firstName) : authors.firstName != null) return false;
        if (middleName != null ? !middleName.equals(authors.middleName) : authors.middleName != null) return false;
        if (birthYear != null ? !birthYear.equals(authors.birthYear) : authors.birthYear != null) return false;
        if (biography != null ? !biography.equals(authors.biography) : authors.biography != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (birthYear != null ? birthYear.hashCode() : 0);
        result = 31 * result + (biography != null ? biography.hashCode() : 0);
        return result;
    }
}
