package library.utils.validation;

import library.dao.DBManagerBook;
import library.dao.ManagerDAO;
import library.dao.entities.Book;

public class BookValidator extends Validator<Book> {

    private static ManagerDAO dao = new DBManagerBook();

    @Override
    public boolean isIdExists(Book entity) {
        return true;
    }

    @Override
    public boolean isNameExists(Book entity) {
        return true;
    }

    @Override
    public boolean canBeUpdated(Book entity) {
        return true;
    }

    @Override
    public boolean canBeCreated(Book entity) {
        return true;
    }

    @Override
    public boolean canBeDeleted(Book entity) {
        return true;
    }

    @Override
    protected boolean isUsed(Book entity) {
        return true;
    }
}
