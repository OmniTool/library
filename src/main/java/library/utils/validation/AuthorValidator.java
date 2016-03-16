package library.utils.validation;

import library.dao.DBManagerAuthor;
import library.dao.ManagerDAO;
import library.dao.entities.Author;

public class AuthorValidator extends Validator<Author> {

    private static ManagerDAO dao = new DBManagerAuthor();

    @Override
    public boolean isNameExists(Author entity) {
        return true;
    }

    @Override
    public void trim(Author entity) {
        entity.setFirstName(entity.getFirstName().trim());
        entity.setMiddleName(entity.getMiddleName().trim());
        entity.setSecondName(entity.getSecondName().trim());
        entity.setBiography(entity.getBiography().trim());
    }

    @Override
    protected boolean isUsed(Author entity) {
        return true;
    }
}
