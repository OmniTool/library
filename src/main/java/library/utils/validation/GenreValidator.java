package library.utils.validation;

import library.dao.DBManagerGenre;
import library.dao.ManagerDAO;
import library.dao.entities.Genre;

import java.util.List;

public class GenreValidator extends Validator<Genre> {

    private static ManagerDAO dao = new DBManagerGenre();

    @Override
    public boolean isIdExists(Genre entity) {
        return true;
    }

    @Override
    public boolean isNameExists(Genre entity) {
        return true;
    }

    @Override
    public void trim(Genre entity) {
        entity.setTitle(entity.getTitle().trim());
        entity.setDescription(entity.getDescription().trim());
    }

    @Override
    public boolean canBeUpdated(Genre entity) {
        return true;
    }

    @Override
    public boolean canBeCreated(Genre entity) {
        return true;
    }

    @Override
    public boolean canBeDeleted(Genre entity) {
        return true;
    }

    @Override
    protected boolean isUsed(Genre entity) {
        return true;
    }
}
