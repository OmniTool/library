package library.utils.validation;

import library.dao.DBManagerBook;
import library.dao.DBManagerGenre;
import library.dao.ManagerDAO;
import library.dao.entities.Book;
import library.dao.entities.Genre;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class BookValidator implements Validator<Book> {

    @Override
    public boolean isNameExists(Book entity) throws SQLException, NamingException {
        ManagerDAO dao = new DBManagerBook();

            List<Book> list = dao.searchEntityByName(entity);
            if (list != null)
                return true;

        return false;
    }

    @Override
    public void trim(Book entity) {
        if (entity.getTitle() != null)
            entity.setTitle(entity.getTitle().trim());
        else
            entity.setTitle("");
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
