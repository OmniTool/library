package library.servlets.crud;

import library.dataAccess.adapters.hibernate.dao.impl.DBManagerGenre;
import library.dataAccess.adapters.hibernate.dao.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.Genre;
import library.dataAccess.validators.impl.GenreValidator;
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

@WebServlet("/editgenre")
public class EditGenre extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = 0;
        String ids = req.getParameter("id");

        if (ids == null) {
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

                if (req.getAttribute("entityCurrent") == null)
                    req.setAttribute("entity", entity);
                else
                    req.setAttribute("entity", req.getAttribute("entityCurrent"));

                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
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
        validator.trim(genre);

            ManagerDAO daoGenre = new DBManagerGenre();

        try {
            boolean isValid; //
            Genre forUpdGenre = (Genre) daoGenre.getEntityById(genre.getId());
            validator.trim(forUpdGenre);
            if (genre.getTitle().equals(forUpdGenre.getTitle())) {
                isValid = true;
            } else {
                isValid = !validator.exists(genre);
            }
            if (isValid) {
                daoGenre.update(genre);
                RequestDispatcher dispatcher = req.getRequestDispatcher("findgenre?id=" + genre.getId());
                dispatcher.forward(req, resp);
            } else {
                req.setAttribute("message", "Уже существует");
                req.setAttribute("entityCurrent", genre);
                doGet(req, resp);
            }
        } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            }

    }
}
