package library.objects;

import java.util.ArrayList;

public class Book {

    private int id;
    private String tittle;
    private int pubYear;
    private int genereId;
    private ArrayList<Integer> authorsIdList;


    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
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

    public ArrayList<Integer> getAuthorsIdList() {
        return authorsIdList;
    }

    public void setAuthorsIdList(ArrayList<Integer> authorsIdList) {
        this.authorsIdList = authorsIdList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
