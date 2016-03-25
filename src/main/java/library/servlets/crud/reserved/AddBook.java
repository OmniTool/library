package library.servlets.crud.reserved;

import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerAuthor;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerGenre;
import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;
import library.dataAccess.adapters.hibernate.entities.GenreAdapter;
import library.dataAccess.validators.Validator;
import library.dataAccess.validators.impl.BookValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addbook")
public class AddBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ManagerDAO dao = new DBManagerAuthor();
        ManagerDAO daoGenre = new DBManagerGenre();
        List<AuthorAdapter> listAuthor = dao.getAll();
        req.setAttribute("sourceListAuthor", listAuthor);
        List<GenreAdapter> listGenre = daoGenre.getAll();
        req.setAttribute("sourceListGenre", listGenre);
        RequestDispatcher dispatcher = req.getRequestDispatcher("addbook.jsp");
        req.setAttribute("bread", "<a href=\"/books\">Книги</a>");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookAdapter book = new BookAdapter();
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
        ManagerDAO daoBook = new DBManagerBook();
        ManagerDAO daoAuthor = new DBManagerAuthor();

//        DBManagerBookAuthor daoBookAuthor = new DBManagerBookAuthor();

        List<AuthorAdapter> listA = new ArrayList<>();
        for (int id : selectedIds) {
            listA.add((AuthorAdapter) daoAuthor.getEntityById(id));
        }
        book.setAuthorsList(listA);
        if (!validator.exists(book)) {

//            int futureId =
                    daoBook.create(book);

//                    //jdbc
//                    for (int id : selectedIds) {
//                        BookAuthor ba = new BookAuthor();
//                        ba.setAuthorId(id);
//                        ba.setBookId(futureId);
//                        daoBookAuthor.create(ba);
//                    }

            RequestDispatcher dispatcher = req.getRequestDispatcher("books");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("message", "Уже существует");
            req.setAttribute("entity", book);
            req.setAttribute("currentListAuthor", listA);
            doGet(req, resp);
        }
    }
}
