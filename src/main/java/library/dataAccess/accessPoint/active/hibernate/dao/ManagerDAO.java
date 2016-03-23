package library.dataAccess.accessPoint.active.hibernate.dao;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface ManagerDAO <E, K> {

    List<E> getAll();
    E getEntityById(K id);
    void update(E entity);
    void delete(E entity);
    int create(E entity);
    List<E> searchEntityByName(E entity);

}
