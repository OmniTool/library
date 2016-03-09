package library.objects;

import java.util.ArrayList;

public class Author {

    private int id;
    private String secondName;
    private String firstName;
    private String middleName;
    private int birthYear;
    private String biography;
    private ArrayList<Integer> booksIdList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public ArrayList<Integer> getBooksIdList() {
        return booksIdList;
    }

    public void setBooksIdList(ArrayList<Integer> booksIdList) {
        this.booksIdList = booksIdList;
    }
}
