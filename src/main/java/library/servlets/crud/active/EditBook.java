package library.servlets.crud.active;

import library.dataAccess.adapters.hibernate.dao.impl.DBManagerAuthor;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBookAuthor;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerGenre;
import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;
import library.dataAccess.adapters.hibernate.entities.GenreAdapter;
import library.dataAccess.validators.impl.BookValidator;
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

@WebServlet("/editbook")
public class EditBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        String ids = req.getParameter("id");
        if (ids == null) {
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("books");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);
            ManagerDAO daoBook = new DBManagerBook();
            ManagerDAO daoGenre = new DBManagerGenre();
            ManagerDAO daoAuthor = new DBManagerAuthor();
            List<AuthorAdapter> listAuthor = daoAuthor.getAll();
            req.setAttribute("sourceListAuthor", listAuthor);
            List<GenreAdapter> listGenre = daoGenre.getAll();
            req.setAttribute("sourceListGenre", listGenre);
            RequestDispatcher dispatcher = req.getRequestDispatcher("editbook.jsp");
            if (req.getAttribute("entity") == null) {
                BookAdapter entity = (BookAdapter) daoBook.getEntityById(id);
                Validator validator = new BookValidator();
                validator.trim(entity);
                req.setAttribute("currentListAuthor", entity.getAuthorsList());
                req.setAttribute("entity", entity);
                req.setAttribute("genre", daoGenre.getEntityById(entity.getGenereId()));
            }
            req.setAttribute("bread", "<a href=\"/books\">Книги</a>");
            req.setAttribute("ref", "/findauthor?id=");
            req.setAttribute("refGenre", "/findgenre?id=");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("id");
        BookAdapter book = new BookAdapter(Integer.parseInt(ids));

        String titles = req.getParameter("title");
        book.setTitle(titles);
        String pubYears = req.getParameter("pubYear");
        book.setPubYear(Integer.parseInt(pubYears));
        String genereIds = req.getParameter("genereId");
        book.setGenereId(Integer.parseInt(genereIds));
        String[] arrAuthors = req.getParameterValues("listAuthor");
        List<Integer> selectedIds = new ArrayList<>();
        if (arrAuthors != null) {
            for (String s : arrAuthors) {
                int id = Integer.parseInt(s);
                selectedIds.add(id);
            }
        }
        Validator validator = new BookValidator();
        ManagerDAO daoAuthor = new DBManagerAuthor();
        ManagerDAO daoBook = new DBManagerBook();
        boolean isValid;
        BookAdapter forUpdBook = (BookAdapter) daoBook.getEntityById(book.getId());
        validator.trim(forUpdBook);
        if (book.getTitle().equals(forUpdBook.getTitle())
                && book.getPubYear() == forUpdBook.getPubYear()
                && book.getGenereId() == forUpdBook.getGenereId()) {
            isValid = true;
        } else {
            isValid = !validator.exists(book);
        }
        List<AuthorAdapter> authors = new ArrayList<>();
        for (int id : selectedIds) {
            authors.add((AuthorAdapter) daoAuthor.getEntityById(id));
        }
        book.setAuthorsList(authors);
        if (isValid) {
            daoBook.update(book);
            RequestDispatcher dispatcher = req.getRequestDispatcher("findbook?id=" + book.getId());
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("message", "Уже существует");
            req.setAttribute("entity", book);
            req.setAttribute("currentListAuthor", authors);
            doGet(req, resp);
        }
    }
}
