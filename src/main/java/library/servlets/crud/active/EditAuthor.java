package library.servlets.crud.active;

import library.dataAccess.accessPoint.DAO;
import library.dataAccess.accessPoint.manageEntities.impl.AuthorBuilder;
import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;
import library.dataAccess.validators.impl.AuthorValidator;
import library.dataAccess.validators.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            DAO dao = new DAO();
            List<BookAdapter> books = dao.getAllBook();
            req.setAttribute("sourceListBook", books);
            RequestDispatcher dispatcher = req.getRequestDispatcher("editauthor.jsp");
            if (req.getAttribute("entity") == null) {
                AuthorAdapter entity = dao.getEntityByIdAuthor(id);
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
        AuthorBuilder entityBuilder = new AuthorBuilder();
        String id = req.getParameter("id");
        AuthorAdapter author = new AuthorAdapter(Integer.parseInt(id));
        entityBuilder.buildEntityFromRequest(author, req);
        List<BookAdapter> currentListBook = author.getBooksList();
        AuthorValidator validator = new AuthorValidator();
        DAO dao = new DAO();
        boolean isValid;
        AuthorAdapter forUpdAuthor = dao.getEntityByIdAuthor(author.getId());
        validator.trim(forUpdAuthor);
        if (author.getFirstName().equals(forUpdAuthor.getFirstName())
                && author.getMiddleName().equals(forUpdAuthor.getMiddleName())
                && author.getSecondName().equals(forUpdAuthor.getSecondName())
                && author.getBirthYear() == forUpdAuthor.getBirthYear()) {
            isValid = true;
        } else {
            isValid = !validator.exists(author);
        }

        if (isValid) {
            dao.update(author);
            RequestDispatcher dispatcher = req.getRequestDispatcher("findauthor?id=" + author.getId());
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("message", "Уже существует");
            req.setAttribute("entity", author);
            req.setAttribute("currentListBook", currentListBook);
            doGet(req, resp);
        }
    }
}
