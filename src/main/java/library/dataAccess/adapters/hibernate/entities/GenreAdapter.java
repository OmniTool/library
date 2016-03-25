package library.dataAccess.adapters.hibernate.entities;

import library.dataAccess.connectors.hibernate.dao.BaseDAO;
import library.dataAccess.connectors.hibernate.dao.impl.DAOGenre;
import library.dataAccess.connectors.hibernate.entities.Genre;


public class GenreAdapter {

    private Genre entity;

    public GenreAdapter() {
        this.entity = new Genre();
    }
    public GenreAdapter(int id) {
        BaseDAO dao = new DAOGenre();
        this.entity = (Genre) dao.getEntityById(id);
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

    public Genre getEntity() {
        return entity;
    }

    public void setEntity(Genre entity) {
        this.entity = entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreAdapter that = (GenreAdapter) o;
        return !(entity != null ? !entity.equals(that.entity) : that.entity != null);
    }

    @Override
    public int hashCode() {
        return entity != null ? entity.hashCode() : 0;
    }
}
