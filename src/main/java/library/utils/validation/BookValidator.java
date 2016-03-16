package library.utils.validation;

import library.dao.DBManagerBook;
import library.dao.ManagerDAO;
import library.dao.entities.Book;

public class BookValidator implements Validator<Book> {

    private static ManagerDAO dao = new DBManagerBook();

    @Override
    public boolean isNameExists(Book entity) {

        return true;
    }

    @Override
    public void trim(Book entity) {
        if (entity.getTitle() != null) entity.setTitle(entity.getTitle().trim());
    }

    @Override
    public boolean isNumber(String str) {
        if (str == null)
            return false;
        return str.matches("-?\\+?\\d+");
    }

    @Override
    public boolean isEmptyString(String str) {
        if(str == null || str.equals("") || str.matches("\\s+")) {
            return true;
        }
        return false;
    }

//    @Override
//    protected boolean isUsed(Book entity) {
//        return true;
//    }
}
