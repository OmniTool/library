package library.utils.validation;

import library.dao.DBManagerGenre;
import library.dao.ManagerDAO;
import library.dao.entities.Genre;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class GenreValidator implements Validator<Genre> {

    @Override
    public boolean isNameExists(Genre entity) throws SQLException, NamingException {
        ManagerDAO dao = new DBManagerGenre();

            List<Genre> list = dao.searchEntityByName(entity);


            if (list != null)
                return true;

        return false;
    }

    @Override
    public void trim(Genre entity) {
        if (entity.getTitle() != null)
            entity.setTitle(entity.getTitle().trim());
        else
            entity.setTitle("");
        if (entity.getDescription() != null)
            entity.setDescription(entity.getDescription().trim());
        else
            entity.setDescription("");
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
