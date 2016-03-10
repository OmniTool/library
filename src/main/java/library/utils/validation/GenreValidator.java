package library.utils.validation;

import library.dao.entities.Genre;

public class GenreValidator implements Validator<Genre> {
    @Override
    public boolean exists(Genre entity) {
        return false;
    }

    @Override
    public boolean isUsed(Genre entity) {
        return false;
    }
}
