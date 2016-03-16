package library.utils.validation;

import library.dao.entities.BookAuthor;

public class BookAuthorValidator extends Validator<BookAuthor> {
    @Override
    public boolean isIdExists(BookAuthor entity) {
        return true;
    }

    @Override
    public boolean isNameExists(BookAuthor entity) {
        return true;
    }

    @Override
    public void trim(BookAuthor entity) {
    }

    @Override
    public boolean canBeUpdated(BookAuthor entity) {
        return true;
    }

    @Override
    public boolean canBeCreated(BookAuthor entity) {
        return true;
    }

    @Override
    public boolean canBeDeleted(BookAuthor entity) {
        return true;
    }

    @Override
    protected boolean isUsed(BookAuthor entity) {
        return true;
    }
}
