package library.dataAccess.adapters.jdbc.entities;

import library.dataAccess.connectors.jdbc.entities.GenreJdbc;

public class Genre {

    private GenreJdbc entity;

    public Genre() {
        this.entity = new GenreJdbc();
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
}
