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
        String secondName = req.getParameter("secondName");
        entity.setSecondName(secondName);
        String firstName = req.getParameter("firstName");
        entity.setFirstName(firstName);
        String middleName = req.getParameter("middleName");
        entity.setMiddleName(middleName);
        String birthYear = req.getParameter("birthYear");
        entity.setBirthYear(Integer.parseInt(birthYear));
        String biography = req.getParameter("biography");
        entity.setBiography(biography);
        String[] booksIds = req.getParameterValues("listBook");
        List<Integer> selectedIds = new ArrayList<>();
        if (booksIds != null) {
            for (String s : booksIds) {
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
