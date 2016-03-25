package library.dataAccess.accessPoint.manageEntities.impl;

import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;
import library.dataAccess.accessPoint.manageEntities.EntityBuilder;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AuthorBuilder implements EntityBuilder<AuthorAdapter> {
    @Override
    public void buildEntityFromRequest(AuthorAdapter entity, HttpServletRequest req) {
        String secondNames = req.getParameter("secondName");
        entity.setSecondName(secondNames);
        String firstNames = req.getParameter("firstName");
        entity.setFirstName(firstNames);
        String middleNames = req.getParameter("middleName");
        entity.setMiddleName(middleNames);
        String birthYears = req.getParameter("birthYear");
        entity.setBirthYear(Integer.parseInt(birthYears));
        String biographys = req.getParameter("biography");
        entity.setBiography(biographys);
        String[] arrBooks = req.getParameterValues("listBook");
        List<Integer> selectedIds = new ArrayList<>();
        if (arrBooks != null) {
            for (String s : arrBooks) {
                int id = Integer.parseInt(s);
                selectedIds.add(id);
            }
        }
        List<BookAdapter> books = new ArrayList<>();
        ManagerDAO daoBook = new DBManagerBook();
        for (int id : selectedIds) {
            books.add((BookAdapter) daoBook.getEntityById(id));
        }
        entity.setBooksList(books);
    }
}
