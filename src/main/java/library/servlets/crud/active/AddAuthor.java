package library.servlets.crud.active;

import library.dataAccess.accessPoint.DAO;
import library.dataAccess.accessPoint.manageEntities.impl.AuthorBuilder;
import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;
import library.dataAccess.validators.impl.AuthorValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addauthor")
public class AddAuthor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAO dao = new DAO();
        List<AuthorAdapter> authors = dao.getAllAuthor();
        req.setAttribute("sourceListBook", authors);
        RequestDispatcher dispatcher = req.getRequestDispatcher("addauthor.jsp");
        req.setAttribute("bread", "<a href=\"/authors\">Авторы</a>");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthorBuilder entityBuilder = new AuthorBuilder();
        AuthorAdapter author = new AuthorAdapter();
        entityBuilder.buildEntityFromRequest(author, req);
        List<BookAdapter> currentListBook = author.getBooksList();
        AuthorValidator validator = new AuthorValidator();
        if (!validator.exists(author)) {
            DAO dao = new DAO();
            dao.create(author);
            RequestDispatcher dispatcher = req.getRequestDispatcher("authors");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("message", "Уже существует");
            req.setAttribute("entity", author);
            req.setAttribute("currentListBook", currentListBook);
            doGet(req, resp);
        }
    }
}
