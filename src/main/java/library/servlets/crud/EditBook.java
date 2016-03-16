package library.servlets.crud;

import library.dao.*;
import library.dao.DBManagerBook;
import library.dao.entities.Author;
import library.dao.entities.Book;
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
import java.util.List;

@WebServlet("/editbook")
public class EditBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = 0;
        String ids = req.getParameter("id");

        if (ids == null) { //TODO проверка на число
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("books");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);

            ManagerDAO daoBook = new DBManagerBook();
            DBManagerBookAuthor daoBookAuthor = new DBManagerBookAuthor();
            ManagerDAO daoGenre = new DBManagerGenre();
            ManagerDAO daoAuthor = new DBManagerAuthor();

            try {
                Book entity = (Book) daoBook.getEntityById(id);
                Validator validator = new BookValidator();
                validator.trim(entity);

                entity.setAuthorsList(daoBookAuthor.searchAuthorsByBook(entity));

                List<Author> listAuthor = daoAuthor.getAll();
                req.setAttribute("sourceListAuthor", listAuthor);
                List<Genre> listGenre = daoGenre.getAll();
                req.setAttribute("sourceListGenre", listGenre);

                RequestDispatcher dispatcher = req.getRequestDispatcher("editbook.jsp");
                req.setAttribute("entity", entity);
                //req.setAttribute("pageName", "Редактирование");
                req.setAttribute("bread", "<a href=\"/books\">Книги</a>");
                req.setAttribute("genre", daoGenre.getEntityById(entity.getGenereId()));
                req.setAttribute("ref", "/findauthor?id=");
                req.setAttribute("refGenre", "/findgenre?id=");
                dispatcher.forward(req, resp);

            } catch (SQLException e) {
                System.out.println(e.getMessage());//TODO отправить на страницу с ошибкой
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Book book = new Book();

        String ids = req.getParameter("id");

            book.setId(Integer.parseInt(ids));

        String titles = req.getParameter("title");

            book.setTitle(titles);

        String pubYears = req.getParameter("pubYear");

            book.setPubYear(Integer.parseInt(pubYears));

        String genereIds = req.getParameter("genereId");

            book.setGenereId(Integer.parseInt(genereIds));


        Validator validator = new BookValidator();
        validator.trim(book);


        if (validator.canBeUpdated(book)) {}

            ManagerDAO dao = new DBManagerBook();
            try {
                dao.update(book);

                RequestDispatcher dispatcher = req.getRequestDispatcher("findbook?id=" + book.getId());
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                System.out.println(e.getMessage());//TODO отправить на страницу с ошибкой
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            }

    }
}
