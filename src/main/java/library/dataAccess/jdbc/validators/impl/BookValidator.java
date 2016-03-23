package library.dataAccess.jdbc.validators.impl;

import library.dataAccess.accessPoint.active.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.accessPoint.active.hibernate.dao.ManagerDAO;
import library.dataAccess.accessPoint.active.hibernate.entities.Book;
import library.dataAccess.jdbc.validators.Validator;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class BookValidator implements Validator<Book> {

    @Override
    public boolean exists(Book entity) throws SQLException, NamingException {
        ManagerDAO dao = new DBManagerBook();
        trim(entity);

        List<Book> list = dao.searchEntityByName(entity);
        String title = entity.getTitle().toUpperCase();
        int pubYear = entity.getPubYear();
        int genreId = entity.getGenereId();

        for (Book e : list) {
            trim(e);
            if (e.getTitle().toUpperCase().equals(title)
                    && e.getPubYear()==pubYear
                    && e.getGenereId()==genreId)
                return true;
        }
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
