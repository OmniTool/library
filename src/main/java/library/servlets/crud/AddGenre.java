package library.servlets.crud;

import library.dao.DBManagerGenre;
import library.dao.ManagerDAO;
import library.dao.entities.Genre;
import library.utils.validation.GenreValidator;
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
import java.util.Enumeration;

@WebServlet("/addgenre")
public class AddGenre extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("addgenre.jsp");

        req.setAttribute("pageName", "Добавление");
        req.setAttribute("bread", "<a href=\"/genres\">Жанры</a>");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Genre genre = new Genre();

        String  titles = req.getParameter("title");

            genre.setTitle(titles );

        String  descriptions = req.getParameter("description");

            genre.setDescription(descriptions );



        Validator validator = new GenreValidator();

        if (validator.canBeCreated(genre)) {
            ManagerDAO dao = new DBManagerGenre();
            try {
                validator.trim(genre);
                dao.create(genre);

                RequestDispatcher dispatcher = req.getRequestDispatcher("genres");
                //req.setAttribute("pageName", "Жанры");
                dispatcher.forward(req, resp);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
