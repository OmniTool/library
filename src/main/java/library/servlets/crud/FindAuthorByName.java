package library.servlets.crud;

import library.dao.DBManagerAuthor;
import library.dao.ManagerDAO;
import library.dao.entities.Author;
import library.utils.validation.AuthorValidator;
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
import java.util.Iterator;
import java.util.List;

@WebServlet("/findauthorbyname")
public class FindAuthorByName extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("authors");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Author author = new Author();

        String secondNames = req.getParameter("secondName"); // поменять

        author.setSecondName(secondNames.trim());

        String firstNames = req.getParameter("firstName");

        author.setFirstName(firstNames.trim());

        String middleNames = req.getParameter("middleName");

        author.setMiddleName(middleNames.trim());

        Validator validator = new AuthorValidator();
        validator.trim(author);

        if (validator.isEmptyString(author.getSecondName()) || validator.isEmptyString(author.getFirstName()) || validator.isEmptyString(author.getMiddleName())) {
            ManagerDAO dao = new DBManagerAuthor();
            int count = 0;

            try {
                List<Author> list = dao.searchEntityByName(author);
                System.out.println(list);
            } catch (SQLException e) {
                System.out.println(e.getMessage());//TODO отправить на страницу с ошибкой
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            }
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("authors");
            dispatcher.forward(req, resp);
        }

    }
}
