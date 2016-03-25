package library.dataAccess.validators.impl;

import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;
import library.dataAccess.validators.Validator;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class BookValidator implements Validator<BookAdapter> {

    @Override
    public boolean exists(BookAdapter entity) {
        ManagerDAO dao = new DBManagerBook();
        trim(entity);

        List<BookAdapter> list = dao.searchEntityByName(entity);
        String title = entity.getTitle().toUpperCase();
        int pubYear = entity.getPubYear();
        int genreId = entity.getGenereId();

        for (BookAdapter e : list) {
            trim(e);
            if (e.getTitle().toUpperCase().equals(title)
                    && e.getPubYear()==pubYear
                    && e.getGenereId()==genreId)
                return true;
        }
        return false;
    }
    @Override
    public void trim(BookAdapter entity) {
        if (entity.getTitle() != null)
            entity.setTitle(entity.getTitle().trim());
        else
            entity.setTitle("");
    }
    @Override
    public boolean isNumber(String str) {
        return str != null && str.matches("-?\\+?\\d+");
    }
    @Override
    public boolean isEmptyString(String str) {
        return str == null || str.equals("") || str.matches("\\s+");
    }
}
