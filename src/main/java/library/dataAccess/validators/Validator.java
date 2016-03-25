package library.dataAccess.validators;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface Validator <E> {

    boolean exists(E entity);
    void trim(E entity);
    boolean isNumber(String str);
    boolean isEmptyString(String str);
}
