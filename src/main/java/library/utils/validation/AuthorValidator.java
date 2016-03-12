package library.utils.validation;

import library.dao.DBManagerAuthor;
import library.dao.ManagerDAO;
import library.dao.entities.Author;

public class AuthorValidator extends Validator<Author> {

    private static ManagerDAO dao = new DBManagerAuthor();

    @Override
    public boolean exists(Author entity) {
        return false;
    }

    @Override
    public boolean canBeUpdated(Author entity) {
        return false;
    }

    @Override
    public boolean canBeCreated(Author entity) {
        return false;
    }

    @Override
    public boolean canBeDeleted(Author entity) {
        return false;
    }

    @Override
    protected boolean isUsed(Author entity) {
        return false;
    }
}
