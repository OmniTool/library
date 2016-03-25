package library.servlets.crud;

import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;
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
import java.util.List;

@WebServlet("/findbookbyname")
public class FindBookByName extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("books");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookAdapter book = new BookAdapter();
        String titles = req.getParameter("title");
        book.setTitle(titles.trim());
        Validator validator = new BookValidator();
        validator.trim(book);
        if (!validator.isEmptyString(book.getTitle())) {
            ManagerDAO dao = new DBManagerBook();
            List<BookAdapter> list = dao.searchEntityByName(book);
            req.setAttribute("list", list);
            req.setAttribute("pageName", book.getTitle());
            req.setAttribute("action", "addbook");
            req.setAttribute("ref", "/findbook?id=");
            RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("books");
            dispatcher.forward(req, resp);
        }
    }
}
