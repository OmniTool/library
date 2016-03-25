package library.servlets.crud.active;

import library.dataAccess.accessPoint.DAO;
import library.dataAccess.accessPoint.manageEntities.impl.BookBuilder;
import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;
import library.dataAccess.adapters.hibernate.entities.GenreAdapter;
import library.dataAccess.validators.impl.BookValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addbook")
public class AddBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAO dao = new DAO();
        List<AuthorAdapter> listAuthor = dao.getAllAuthor();
        req.setAttribute("sourceListAuthor", listAuthor);
        List<GenreAdapter> listGenre = dao.getAllGenre();
        req.setAttribute("sourceListGenre", listGenre);
        RequestDispatcher dispatcher = req.getRequestDispatcher("addbook.jsp");
        req.setAttribute("bread", "<a href=\"/books\">Книги</a>");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookBuilder entityBuilder = new BookBuilder();
        BookAdapter book = new BookAdapter();
        entityBuilder.buildEntityFromRequest(book, req);
        List<AuthorAdapter> currentListAuthor = book.getAuthorsList();
        BookValidator validator = new BookValidator();
        if (!validator.exists(book)) {
            DAO dao = new DAO();
            dao.create(book);
            RequestDispatcher dispatcher = req.getRequestDispatcher("books");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("message", "Уже существует");
            req.setAttribute("entity", book);
            req.setAttribute("currentListAuthor", currentListAuthor);
            doGet(req, resp);
        }
    }
}
