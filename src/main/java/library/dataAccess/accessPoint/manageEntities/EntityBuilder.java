package library.dataAccess.accessPoint.manageEntities;

import javax.servlet.http.HttpServletRequest;

public interface EntityBuilder<E> {
    void buildEntityFromRequest(E entity, HttpServletRequest req);
}
