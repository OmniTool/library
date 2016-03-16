package library.utils.validation;

import library.dao.DBManagerAuthor;
import library.dao.ManagerDAO;
import library.dao.entities.Author;

public class AuthorValidator implements Validator<Author> {

    private static ManagerDAO dao = new DBManagerAuthor();

    @Override
    public boolean isNameExists(Author entity) {

        return true;
    }

    @Override
    public void trim(Author entity) {
        if (entity.getFirstName() != null) entity.setFirstName(entity.getFirstName().trim());
        if (entity.getMiddleName() != null) entity.setMiddleName(entity.getMiddleName().trim());
        if (entity.getSecondName() != null) entity.setSecondName(entity.getSecondName().trim());
        if (entity.getBiography() != null) entity.setBiography(entity.getBiography().trim());
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
//    protected boolean isUsed(Author entity) {
//        return true;
//    }
}
