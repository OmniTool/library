package library.utils.validation;

import library.dao.DBManagerBook;
import library.dao.ManagerDAO;
import library.dao.entities.Book;

public class BookValidator extends Validator<Book> {

    private static ManagerDAO dao = new DBManagerBook();

    @Override
    public boolean exists(Book entity) {
        return false;
    }

    @Override
    public boolean canBeUpdated(Book entity) {
        return false;
    }

    @Override
    public boolean canBeCreated(Book entity) {
        return false;
    }

    @Override
    public boolean canBeDeleted(Book entity) {
        return false;
    }

    @Override
    protected boolean isUsed(Book entity) {
        return false;
    }
}
