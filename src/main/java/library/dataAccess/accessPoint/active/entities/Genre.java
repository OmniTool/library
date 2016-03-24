package library.dataAccess.accessPoint.active.entities;

import library.dataAccess.hibernate.entities.GenreHiber;


public class Genre implements EntityBase {

    private GenreHiber entity;

    public Genre() {
        this.entity = new GenreHiber();
    }

    public Genre(GenreHiber entity) {
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
