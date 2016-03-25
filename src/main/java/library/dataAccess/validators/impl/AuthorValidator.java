package library.dataAccess.validators.impl;

import library.dataAccess.adapters.hibernate.dao.impl.DBManagerAuthor;
import library.dataAccess.adapters.hibernate.dao.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.Author;
import library.dataAccess.validators.Validator;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class AuthorValidator implements Validator<Author> {

    @Override
    public boolean exists(Author entity) throws SQLException, NamingException {
        ManagerDAO dao = new DBManagerAuthor();
        trim(entity);

        List<Author> list = dao.searchEntityByName(entity);
        String firstName = entity.getFirstName().toUpperCase();
        String middleName = entity.getMiddleName().toUpperCase();
        String secondName = entity.getSecondName().toUpperCase();
        int birthYear = entity.getBirthYear();

        for (Author e : list) {
            trim(e);
            String firstNameE = e.getFirstName().toUpperCase();
            String middleNameE = e.getMiddleName().toUpperCase();
            String secondNameE = e.getSecondName().toUpperCase();
            int birthYearE = e.getBirthYear();
                if (middleNameE.equals(middleName)
                        && firstNameE.equals(firstName)
                        && secondNameE.equals(secondName)
                        && birthYearE==birthYear)
                    return true;
        }
        return false;
    }
    @Override
    public void trim(Author entity) {
        if (entity.getFirstName() != null)
            entity.setFirstName(entity.getFirstName().trim());
        else
            entity.setFirstName("");
        if (entity.getMiddleName() != null)
            entity.setMiddleName(entity.getMiddleName().trim());
        else
            entity.setMiddleName("");
        if (entity.getSecondName() != null)
            entity.setSecondName(entity.getSecondName().trim());
        else
            entity.setSecondName("");
        if (entity.getBiography() != null)
            entity.setBiography(entity.getBiography().trim());
        else
            entity.setBiography("");
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
}
