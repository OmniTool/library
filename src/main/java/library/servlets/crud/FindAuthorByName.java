package library.servlets.crud;

import library.dataAccess.accessPoint.active.hibernate.dao.impl.DBManagerAuthor;
import library.dataAccess.accessPoint.active.hibernate.dao.ManagerDAO;
import library.dataAccess.accessPoint.active.hibernate.entities.Author;
import library.dataAccess.jdbc.validators.impl.AuthorValidator;
import library.dataAccess.jdbc.validators.Validator;

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

        if (!validator.isEmptyString(author.getSecondName()) || !validator.isEmptyString(author.getFirstName()) || !validator.isEmptyString(author.getMiddleName())) {
            ManagerDAO dao = new DBManagerAuthor();

            try {
                List<Author> list = dao.searchEntityByName(author);

                req.setAttribute("list", list);
                req.setAttribute("pageName", author.getFirstName() + " " + author.getMiddleName() + " " + author.getSecondName());
                req.setAttribute("action", "addauthor");
                req.setAttribute("ref", "/findauthor?id=");
                RequestDispatcher dispatcher = req.getRequestDispatcher("authorlist.jsp");

                dispatcher.forward(req, resp);

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
