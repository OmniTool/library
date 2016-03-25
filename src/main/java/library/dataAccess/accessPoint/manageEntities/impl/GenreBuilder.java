package library.dataAccess.accessPoint.manageEntities.impl;

import library.dataAccess.adapters.hibernate.entities.GenreAdapter;
import library.dataAccess.accessPoint.manageEntities.EntityBuilder;

import javax.servlet.http.HttpServletRequest;

public class GenreBuilder implements EntityBuilder<GenreAdapter> {
    @Override
    public void buildEntityFromRequest(GenreAdapter entity, HttpServletRequest req) {
        String titles = req.getParameter("title");
        entity.setTitle(titles);
        String descriptions = req.getParameter("description");
        entity.setDescription(descriptions);
    }
}
