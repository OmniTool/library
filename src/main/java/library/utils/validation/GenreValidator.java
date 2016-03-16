package library.utils.validation;

import library.dao.DBManagerGenre;
import library.dao.ManagerDAO;
import library.dao.entities.Genre;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class GenreValidator implements Validator<Genre> {

    private static ManagerDAO dao = new DBManagerGenre();

    @Override
    public boolean isNameExists(Genre entity) {
        ManagerDAO dao = new DBManagerGenre();
        try {
            List<Genre> list = dao.searchEntityByName(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void trim(Genre entity) {
        if (entity.getTitle() != null) entity.setTitle(entity.getTitle().trim());
        if (entity.getDescription() != null) entity.setDescription(entity.getDescription().trim());
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
//    protected boolean isUsed(Genre entity) {
//        return true;
//    }
}
