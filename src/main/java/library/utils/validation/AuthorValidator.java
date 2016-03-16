package library.utils.validation;

import library.dao.DBManagerAuthor;
import library.dao.ManagerDAO;
import library.dao.entities.Author;

public class AuthorValidator extends Validator<Author> {

    private static ManagerDAO dao = new DBManagerAuthor();

    @Override
    public boolean isIdExists(Author entity) {
        return true;
    }

    @Override
    public boolean isNameExists(Author entity) {
        return true;
    }

    @Override
    public void trim(Author entity) {
    }

    @Override
    public boolean canBeUpdated(Author entity) {
        return true;
    }

    @Override
    public boolean canBeCreated(Author entity) {
        return true;
    }

    @Override
    public boolean canBeDeleted(Author entity) {
        return true;
    }

    @Override
    protected boolean isUsed(Author entity) {
        return true;
    }
}
