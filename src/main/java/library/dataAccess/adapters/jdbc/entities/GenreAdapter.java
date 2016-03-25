package library.dataAccess.adapters.jdbc.entities;

import library.dataAccess.connectors.jdbc.entities.Genre;

public class GenreAdapter {

    private Genre entity;

    public GenreAdapter() {
        this.entity = new Genre();
    }

    public GenreAdapter(Genre entity) {
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
}
