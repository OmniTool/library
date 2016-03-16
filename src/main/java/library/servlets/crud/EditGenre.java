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
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("books");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);


            RequestDispatcher dispatcher = req.getRequestDispatcher("editgenre.jsp");
            req.setAttribute("bread", "<a href=\"/genres\">Книги</a>");
            //req.setAttribute("entity", entity);
            dispatcher.forward(req, resp);
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
                dao.update(genre);


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
