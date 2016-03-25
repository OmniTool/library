package library.servlets.crud.active;

import library.dataAccess.adapters.hibernate.dao.impl.DBManagerAuthor;
import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBookAuthor;
import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;
import library.dataAccess.validators.impl.AuthorValidator;
import library.dataAccess.validators.Validator;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/editauthor")
public class EditAuthor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        String ids = req.getParameter("id");
        if (ids == null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("authors");
            dispatcher.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);
            ManagerDAO daoAuthor = new DBManagerAuthor();
            ManagerDAO daoBook = new DBManagerBook();
            List<BookAdapter> books = daoBook.getAll();
            req.setAttribute("sourceListBook", books);
            RequestDispatcher dispatcher = req.getRequestDispatcher("editauthor.jsp");

            if (req.getAttribute("entity") == null) {
                AuthorAdapter entity = (AuthorAdapter) daoAuthor.getEntityById(id);
                Validator validator = new AuthorValidator();
                validator.trim(entity);
                req.setAttribute("currentListBook", entity.getBooksList());
                req.setAttribute("entity", entity);
            }
            req.setAttribute("bread", "<a href=\"/authors\">Авторы</a>");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("id");
        AuthorAdapter author = new AuthorAdapter(Integer.parseInt(ids));
        String secondNames = req.getParameter("secondName");
        author.setSecondName(secondNames);
        String firstNames = req.getParameter("firstName");
        author.setFirstName(firstNames);
        String middleNames = req.getParameter("middleName");
        author.setMiddleName(middleNames);
        String birthYears = req.getParameter("birthYear");
        author.setBirthYear(Integer.parseInt(birthYears));
        String biographys = req.getParameter("biography");
        author.setBiography(biographys);
        String[] arrBooks = req.getParameterValues("listBook");
        List<Integer> selectedBooksId = new ArrayList<>();
        if (arrBooks != null) {
            for (String s : arrBooks) {
                int id = Integer.parseInt(s);
                selectedBooksId.add(id);
            }
        }
        Validator validator = new AuthorValidator();
        ManagerDAO daoAuthor = new DBManagerAuthor();
        ManagerDAO daoBook = new DBManagerBook();
        boolean isValid;
        AuthorAdapter forUpdAuthor = (AuthorAdapter) daoAuthor.getEntityById(author.getId());
        validator.trim(forUpdAuthor);
        if (author.getFirstName().equals(forUpdAuthor.getFirstName())
                && author.getMiddleName().equals(forUpdAuthor.getMiddleName())
                && author.getSecondName().equals(forUpdAuthor.getSecondName())
                && author.getBirthYear() == forUpdAuthor.getBirthYear()) {
            isValid = true;
        } else {
            isValid = !validator.exists(author);
        }
        List<BookAdapter> books = new ArrayList<>();
        for (int id : selectedBooksId) {
            books.add((BookAdapter) daoBook.getEntityById(id));
        }
        author.setBooksList(books);
        if (isValid) {
            daoAuthor.update(author);
            RequestDispatcher dispatcher = req.getRequestDispatcher("findauthor?id=" + author.getId());
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("message", "Уже существует");
            req.setAttribute("entity", author);
            req.setAttribute("currentListBook", books);
            doGet(req, resp);
        }
    }
}
