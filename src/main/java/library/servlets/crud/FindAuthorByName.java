package library.servlets.crud;

import library.dataAccess.accessPoint.DAO;
import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;
import library.dataAccess.validators.impl.AuthorValidator;
import library.dataAccess.validators.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        AuthorAdapter author = new AuthorAdapter();
        String secondNames = req.getParameter("secondName");
        author.setSecondName(secondNames.trim());
        String firstNames = req.getParameter("firstName");
        author.setFirstName(firstNames.trim());
        String middleNames = req.getParameter("middleName");
        author.setMiddleName(middleNames.trim());
        Validator validator = new AuthorValidator();
        validator.trim(author);
        if (!validator.isEmptyString(author.getSecondName()) || !validator.isEmptyString(author.getFirstName()) || !validator.isEmptyString(author.getMiddleName())) {
            DAO dao = new DAO();
            List<AuthorAdapter> list = dao.searchEntityByName(author);
            req.setAttribute("list", list);
            req.setAttribute("pageName", author.getFirstName() + " " + author.getMiddleName() + " " + author.getSecondName());
            req.setAttribute("action", "addauthor");
            req.setAttribute("ref", "/findauthor?id=");
            RequestDispatcher dispatcher = req.getRequestDispatcher("authorlist.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("authors");
            dispatcher.forward(req, resp);
        }
    }
}
