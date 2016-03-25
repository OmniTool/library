package library.servlets.crud.active;

import library.dataAccess.accessPoint.DAO;
import library.dataAccess.accessPoint.manageEntities.EntityBuilder;
import library.dataAccess.accessPoint.manageEntities.impl.BookBuilder;
import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;
import library.dataAccess.adapters.hibernate.entities.GenreAdapter;
import library.dataAccess.validators.impl.BookValidator;
import library.dataAccess.validators.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            DAO dao = new DAO();
            List<AuthorAdapter> listAuthor = dao.getAllAuthor();
            req.setAttribute("sourceListAuthor", listAuthor);
            List<GenreAdapter> listGenre = dao.getAllGenre();
            req.setAttribute("sourceListGenre", listGenre);
            RequestDispatcher dispatcher = req.getRequestDispatcher("editbook.jsp");
            if (req.getAttribute("entity") == null) {
                BookAdapter entity = dao.getEntityByIdBook(id);
                Validator validator = new BookValidator();
                validator.trim(entity);
                req.setAttribute("currentListAuthor", entity.getAuthorsList());
                req.setAttribute("entity", entity);
                req.setAttribute("genre", dao.getEntityByIdGenre(entity.getGenereId()));
            }
            req.setAttribute("bread", "<a href=\"/books\">Книги</a>");
            req.setAttribute("ref", "/findauthor?id=");
            req.setAttribute("refGenre", "/findgenre?id=");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityBuilder entityBuilder = new BookBuilder();
        String ids = req.getParameter("id");
        BookAdapter book = new BookAdapter(Integer.parseInt(ids));
        entityBuilder.buildEntityFromRequest(book, req);
        List<AuthorAdapter> currentListAuthor = book.getAuthorsList();
        BookValidator validator = new BookValidator();
        DAO dao = new DAO();
        boolean isValid;
        BookAdapter forUpdBook = dao.getEntityByIdBook(book.getId());
        validator.trim(forUpdBook);
        if (book.equals(forUpdBook) && book.getGenereId() == forUpdBook.getGenereId()) {
            isValid = true;
        } else {
            isValid = !validator.exists(book);
        }
        if (isValid) {
            dao.update(book);
            RequestDispatcher dispatcher = req.getRequestDispatcher("findbook?id=" + book.getId());
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("message", "Уже существует");
            req.setAttribute("entity", book);
            req.setAttribute("currentListAuthor", currentListAuthor);
            doGet(req, resp);
        }
    }
}
