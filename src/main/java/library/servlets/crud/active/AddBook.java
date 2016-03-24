package library.servlets.crud.active;

import library.dataAccess.adapters.hibernate.dao.impl.DBManagerAuthor;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.adapters.hibernate.dao.ManagerDAO;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBookAuthor;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerGenre;
import library.dataAccess.adapters.hibernate.entities.Author;
import library.dataAccess.adapters.hibernate.entities.Book;
import library.dataAccess.adapters.hibernate.entities.Genre;
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
        RequestDispatcher dispatcher = req.getRequestDispatcher("addbook.jsp");
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
        DBManagerBookAuthor daoBookAuthor = new DBManagerBookAuthor();
            try {
                List<Author> listA = new ArrayList<>();
                for (int id : selectedIds) {
                    listA.add((Author) daoAuthor.getEntityById(id));
                }
                book.setAuthorsList(listA);
                if (!validator.exists(book)) {
                    int futureId = daoBook.create(book);

//                    //jdbc
//                    for (int id : selectedIds) {
//                        BookAuthor ba = new BookAuthor();
//                        ba.setAuthorId(id);
//                        ba.setBookId(futureId);
//                        daoBookAuthor.create(ba);
//                    }

                    RequestDispatcher dispatcher = req.getRequestDispatcher("books");
                    dispatcher.forward(req, resp);;
                } else {
                    req.setAttribute("message", "Уже существует");
                    req.setAttribute("entity", book);
                    req.setAttribute("currentListAuthor", listA);
                    doGet(req, resp);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            }

    }
}
