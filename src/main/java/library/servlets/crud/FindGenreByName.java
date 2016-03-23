package library.servlets.crud;

import library.dataAccess.accessPoint.dao.impl.DBManagerGenre;
import library.dataAccess.accessPoint.dao.ManagerDAO;
import library.dataAccess.accessPoint.entities.Genre;
import library.dataAccess.jdbc.validators.impl.GenreValidator;
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

@WebServlet("/findgenrebyname")
public class FindGenreByName extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("genres");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Genre genre = new Genre();

        String titles = req.getParameter("title");

            genre.setTitle(titles.trim());

        Validator validator = new GenreValidator();
        validator.trim(genre);

        if (!validator.isEmptyString(genre.getTitle())) {
            ManagerDAO dao = new DBManagerGenre();
            try {
                List<Genre> list = dao.searchEntityByName(genre);

                req.setAttribute("list", list);
                req.setAttribute("pageName", genre.getTitle());
                req.setAttribute("action", "addgenre");
                req.setAttribute("ref", "/findgenre?id=");

                RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");

                dispatcher.forward(req, resp);

            } catch (SQLException e) {
                System.out.println(e.getMessage());//TODO отправить на страницу с ошибкой
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            }
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("genres");
            dispatcher.forward(req, resp);
        }
    }
}
