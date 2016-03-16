package library.utils.validation;

import library.dao.DBManagerGenre;
import library.dao.ManagerDAO;

public abstract class Validator <E> {

    public abstract boolean isIdExists(E entity);
    public abstract boolean isNameExists(E entity);

    public abstract void trim(E entity);

    public abstract boolean canBeUpdated(E entity);
    public abstract boolean canBeCreated(E entity);
    public abstract boolean canBeDeleted(E entity);

    protected abstract boolean isUsed(E entity);

    private boolean isIntegerNumber(String str) { //число, +число, -число
        if (str == null)
            return false;
        return str.matches("^-?\\+?\\d+$");
    }

    private boolean isEmptyString(String str) {
        if(str == null || str.equals("")) {
            return true;
        }
        return false;
    }





}
