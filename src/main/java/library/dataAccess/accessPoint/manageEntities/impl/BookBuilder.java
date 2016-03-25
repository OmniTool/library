package library.dataAccess.accessPoint.manageEntities.impl;

import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerAuthor;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;
import library.dataAccess.accessPoint.manageEntities.EntityBuilder;
import library.dataAccess.validators.Validator;
import library.dataAccess.validators.impl.BookValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class BookBuilder implements EntityBuilder<BookAdapter> {
    @Override
    public void buildEntityFromRequest(BookAdapter entity, HttpServletRequest req) {
        String title = req.getParameter("title");
        entity.setTitle(title);
        String pubYear = req.getParameter("pubYear");
        entity.setPubYear(Integer.parseInt(pubYear));
        String genereId = req.getParameter("genereId");
        entity.setGenereId(Integer.parseInt(genereId));
        String[] authorIds = req.getParameterValues("listAuthor");
        List<Integer> selectedIds = new ArrayList<>();
        if (authorIds != null) {
            for (String s : authorIds) {
                int id = Integer.parseInt(s);
                selectedIds.add(id);
            }
        }
        ManagerDAO daoAuthor = new DBManagerAuthor();
        List<AuthorAdapter> authors = new ArrayList<>();
        for (int id : selectedIds) {
            authors.add((AuthorAdapter) daoAuthor.getEntityById(id));
        }
        entity.setAuthorsList(authors);
    }
}
