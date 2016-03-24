package library.dataAccess.validators.impl;

import library.dataAccess.adapters.hibernate.dao.impl.DBManagerGenre;
import library.dataAccess.adapters.hibernate.dao.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.Genre;
import library.dataAccess.validators.Validator;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class GenreValidator implements Validator<Genre> {

    @Override
    public boolean exists(Genre entity) throws SQLException, NamingException {
        ManagerDAO dao = new DBManagerGenre();
        trim(entity);

        List<Genre> list = dao.searchEntityByName(entity);
        String title = entity.getTitle().toUpperCase();

        for (Genre e : list) {
            trim(e);
            if (e.getTitle().toUpperCase().equals(title))
                return true;
        }
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
