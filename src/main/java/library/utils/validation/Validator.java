package library.utils.validation;

import library.dao.DBManagerGenre;
import library.dao.ManagerDAO;

public interface Validator <E> {

    boolean isNameExists(E entity);

    void trim(E entity);

    boolean isNumber(String str);

    boolean isEmptyString(String str);

//    protected abstract boolean isUsed(E entity);

//    private boolean isNumber(String str) { //число, +число, -число
//        if (str == null)
//            return false;
//        return str.matches("-?\\+?\\d+");
//    }
//
//    private boolean isEmptyString(String str) {
//        if(str == null || str.equals("") || str.matches("\\s+")) {
//            return true;
//        }
//        return false;
//    }





}
