package library.utils.validation;

import library.dao.entities.BookAuthor;

public class BookAuthorValidator extends Validator<BookAuthor> {

    @Override
    public boolean isNameExists(BookAuthor entity) {
        return true;
    }

    @Override
    public void trim(BookAuthor entity) {
    }

    @Override
    protected boolean isUsed(BookAuthor entity) {
        return true;
    }
}
