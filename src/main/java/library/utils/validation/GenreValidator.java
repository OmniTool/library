package library.utils.validation;

import library.dao.DBManagerGenre;
import library.dao.ManagerDAO;
import library.dao.entities.Genre;

import java.util.List;

public class GenreValidator extends Validator<Genre> {

    private static ManagerDAO dao = new DBManagerGenre();

    @Override
    public boolean isIdExists(Genre entity) {
        return false;
    }

    @Override
    public boolean isNameExists(Genre entity) {
        return false;
    }

    @Override
    public boolean canBeUpdated(Genre entity) {
        return false;
    }

    @Override
    public boolean canBeCreated(Genre entity) {
        return false;
    }

    @Override
    public boolean canBeDeleted(Genre entity) {
        return false;
    }

    @Override
    protected boolean isUsed(Genre entity) {
        return false;
    }
}
