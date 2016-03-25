package library.dataAccess.validators.impl;

import library.dataAccess.adapters.hibernate.dao.impl.DBManagerGenre;
import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.GenreAdapter;
import library.dataAccess.validators.Validator;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class GenreValidator implements Validator<GenreAdapter> {

    @Override
    public boolean exists(GenreAdapter entity) {
        ManagerDAO dao = new DBManagerGenre();
        trim(entity);

        List<GenreAdapter> list = dao.searchEntityByName(entity);
        String title = entity.getTitle().toUpperCase();

        for (GenreAdapter e : list) {
            trim(e);
            if (e.getTitle().toUpperCase().equals(title))
                return true;
        }
        return false;
    }
    @Override
    public void trim(GenreAdapter entity) {
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
        return str != null && str.matches("-?\\+?\\d+");
    }
    @Override
    public boolean isEmptyString(String str) {
        return str == null || str.equals("") || str.matches("\\s+");
    }
}
