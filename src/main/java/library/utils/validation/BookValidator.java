package library.utils.validation;

import library.dao.DBManagerBook;
import library.dao.ManagerDAO;
import library.dao.entities.Book;

public class BookValidator extends Validator<Book> {

    private static ManagerDAO dao = new DBManagerBook();

    @Override
    public boolean isNameExists(Book entity) {
        return true;
    }

    @Override
    public void trim(Book entity) {
        entity.setTitle(entity.getTitle().trim());
    }

    @Override
    protected boolean isUsed(Book entity) {
        return true;
    }
}
