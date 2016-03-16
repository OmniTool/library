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

@WebServlet("/editgenre")
public class EditGenre extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = 0;
        String ids = req.getParameter("id");

        if (ids == null) { //TODO проверка на число
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("genres");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);

            ManagerDAO daoGenre = new DBManagerGenre();

            try {
                Genre entity = (Genre) daoGenre.getEntityById(id);
                Validator validator = new GenreValidator();
                validator.trim(entity);

                RequestDispatcher dispatcher = req.getRequestDispatcher("editgenre.jsp");
                req.setAttribute("bread", "<a href=\"/genres\">Жанры</a>");
                req.setAttribute("entity", entity);
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

        Genre genre = new Genre();

        String ids = req.getParameter("id");

            genre.setId(Integer.parseInt(ids)); 

        String titles = req.getParameter("title");

            genre.setTitle(titles);

        String descriptions = req.getParameter("description");

            genre.setDescription(descriptions);


        Validator validator = new GenreValidator();

        if (validator.canBeUpdated(genre)) {
            ManagerDAO dao = new DBManagerGenre();
            try {
                validator.trim(genre);
                dao.update(genre);

                RequestDispatcher dispatcher = req.getRequestDispatcher("findgenre?id=" + genre.getId());
                dispatcher.forward(req, resp);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
