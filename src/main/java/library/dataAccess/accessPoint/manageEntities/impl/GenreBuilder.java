package library.dataAccess.accessPoint.manageEntities.impl;

import library.dataAccess.adapters.hibernate.entities.GenreAdapter;
import library.dataAccess.accessPoint.manageEntities.EntityBuilder;

import javax.servlet.http.HttpServletRequest;

public class GenreBuilder implements EntityBuilder<GenreAdapter> {
    @Override
    public void buildEntityFromRequest(GenreAdapter entity, HttpServletRequest req) {
        String title = req.getParameter("title");
        entity.setTitle(title);
        String description = req.getParameter("description");
        entity.setDescription(description);
    }
}
