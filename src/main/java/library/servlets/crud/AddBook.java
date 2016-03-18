package library.servlets.crud;

import library.dao.*;
import library.dao.DBManagerBook;
import library.dao.entities.Author;
import library.dao.entities.Book;
import library.dao.entities.Genre;
import library.utils.validation.BookValidator;
import library.utils.validation.Validator;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addbook")
public class AddBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ManagerDAO dao = new DBManagerAuthor();
        ManagerDAO daoGenre = new DBManagerGenre();
        try {
            List<Author> listAuthor = dao.getAll();
            req.setAttribute("sourceListAuthor", listAuthor);
            List<Genre> listGenre = daoGenre.getAll();
            req.setAttribute("sourceListGenre", listGenre);
        } catch (SQLException e) {
            System.out.println(e.getMessage());//TODO отправить на страницу с ошибкой
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }
        //req.setAttribute("resultListAuthor", new ArrayList<Author>());
//        if (req.getAttribute("entityCurrent") == null)
//            req.setAttribute("entity", entity);
//        else
            //req.setAttribute("entity", req.getAttribute("entityCurrent"));

        RequestDispatcher dispatcher = req.getRequestDispatcher("addbook.jsp");
        //req.setAttribute("pageName", "Добавление");
        req.setAttribute("bread", "<a href=\"/books\">Книги</a>");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Book book = new Book();

        String  titles = req.getParameter("title");
            book.setTitle(titles );
        String  pubYears = req.getParameter("pubYear");
            book.setPubYear(Integer.parseInt(pubYears ));
        String  genereIds = req.getParameter("genereId");
            book.setGenereId(Integer.parseInt(genereIds ));
        String[] arrBooks = req.getParameterValues("listBook");
        List<Integer> selectedBooksId = new ArrayList<>();

        for (String s : arrBooks) {
            int id = Integer.parseInt(s);
            selectedBooksId.add(id);
        }

        Validator validator = new BookValidator();

        ManagerDAO dao = new DBManagerBook();
            try {
                if (!validator.exists(book)) {
                    dao.create(book);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("books");
                    dispatcher.forward(req, resp);;
                } else {
                    req.setAttribute("message", "Уже существует");
                    req.setAttribute("entity", book);
                    doGet(req, resp);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            }

    }
}
