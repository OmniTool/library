package library.utils.validation;

import library.dao.DBManagerGenre;
import library.dao.ManagerDAO;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface Validator <E> {

    boolean isNameExists(E entity) throws SQLException, NamingException;

    void trim(E entity);

    boolean isNumber(String str);

    boolean isEmptyString(String str);

    //boolean isUsed(E entity);

}
