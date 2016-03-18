package library.servlets.crud;

import library.dao.*;
import library.dao.DBManagerAuthor;
import library.dao.entities.Author;
import library.dao.entities.Book;
import library.dao.entities.BookAuthor;
import library.dao.entities.Genre;
import library.utils.validation.AuthorValidator;
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

@WebServlet("/addauthor")
public class AddAuthor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ManagerDAO dao = new DBManagerBook();

        try {
            List<Author> list = dao.getAll();
            req.setAttribute("sourceListBook", list);
        } catch (SQLException e) {
            System.out.println(e.getMessage());//TODO отправить на страницу с ошибкой
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }
        //req.setAttribute("resultListBook", new ArrayList<Author>());
//        if (req.getAttribute("entityCurrent") == null)
//            req.setAttribute("entity", entity);
//        else
//            req.setAttribute("entity", req.getAttribute("entityCurrent"));

        RequestDispatcher dispatcher = req.getRequestDispatcher("addauthor.jsp");

        req.setAttribute("pageName", "Добавление");
        req.setAttribute("bread", "<a href=\"/authors\">Авторы</a>");

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
        Author author = new Author();

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

        for (String s : arrBooks) {
            int id = Integer.parseInt(s);
            selectedBooksId.add(id);
        }

        Validator validator = new AuthorValidator();
        ManagerDAO daoAuthor = new DBManagerAuthor();
        ManagerDAO daoBook = new DBManagerBook();
        DBManagerBookAuthor daoBookAuthor = new DBManagerBookAuthor();
            try {
                if (!validator.exists(author)) {
                    int futureId = daoAuthor.create(author);

                    for (String s : arrBooks) {
                        BookAuthor ba = new BookAuthor();
                        ba.setAuthorId(futureId);
                        ba.setBookId(Integer.parseInt(s));
                        daoBookAuthor.create(ba);
                    }

                    RequestDispatcher dispatcher = req.getRequestDispatcher("authors");
                    dispatcher.forward(req, resp);
                } else {
                    List<Book> l = new ArrayList<>();
                    for (int id : selectedBooksId) {
                        l.add((Book) daoBook.getEntityById(id));
                    }
                    author.setBooksList(l);
                    req.setAttribute("message", "Уже существует");
                    req.setAttribute("entity", author);
                    doGet(req, resp);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            }

    }
}
