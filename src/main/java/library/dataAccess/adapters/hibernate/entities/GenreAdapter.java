package library.dataAccess.adapters.hibernate.entities;

import library.dataAccess.connectors.hibernate.entities.GenreHiber;


public class GenreAdapter {

    private GenreHiber entity;

    public GenreAdapter() {
        this.entity = new GenreHiber();
    }

    public GenreAdapter(GenreHiber entity) {
        this.entity = entity;
    }

    public int getId() {
        return entity.getId();
    }

    public void setId(int id) {
        entity.setId(id);
    }

    public String getTitle() {
        return entity.getTitle();
    }

    public void setTitle(String title) {
        entity.setTitle(title);
    }

    public String getDescription() {
        return entity.getDescription();
    }

    public void setDescription(String description) {
        entity.setDescription(description);
    }

    @Override
    public String toString() {
        return entity.toString();
    }

    public GenreHiber getEntity() {
        return entity;
    }

    public void setEntity(GenreHiber entity) {
        this.entity = entity;
    }
}
