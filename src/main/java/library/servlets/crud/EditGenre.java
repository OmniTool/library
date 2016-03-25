package library.servlets.crud;

import library.dataAccess.accessPoint.DAO;
import library.dataAccess.accessPoint.manageEntities.EntityBuilder;
import library.dataAccess.accessPoint.manageEntities.impl.GenreBuilder;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerGenre;
import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.GenreAdapter;
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
        int id;
        String ids = req.getParameter("id");
        if (ids == null) {
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("genres");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);
            DAO dao = new DAO();
            GenreAdapter entity = dao.getEntityByIdGenre(id);
            Validator validator = new GenreValidator();
            validator.trim(entity);
            RequestDispatcher dispatcher = req.getRequestDispatcher("editgenre.jsp");
            req.setAttribute("bread", "<a href=\"/genres\">Жанры</a>");
            if (req.getAttribute("entityCurrent") == null)
                req.setAttribute("entity", entity);
            else
                req.setAttribute("entity", req.getAttribute("entityCurrent"));
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenreBuilder entityBuilder = new GenreBuilder();
        String id = req.getParameter("id");
        GenreAdapter genre = new GenreAdapter(Integer.parseInt(id));
        entityBuilder.buildEntityFromRequest(genre, req);
        GenreValidator validator = new GenreValidator();
        validator.trim(genre);
        DAO dao = new DAO();
        boolean isValid;
        GenreAdapter forUpdGenre = dao.getEntityByIdGenre(genre.getId());
        validator.trim(forUpdGenre);
        if (genre.getTitle().equals(forUpdGenre.getTitle())) {
            isValid = true;
        } else {
            isValid = !validator.exists(genre);
        }
        if (isValid) {
            dao.update(genre);
            RequestDispatcher dispatcher = req.getRequestDispatcher("findgenre?id=" + genre.getId());
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("message", "Уже существует");
            req.setAttribute("entityCurrent", genre);
            doGet(req, resp);
        }
    }
}
