package library.servlets.crud;

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
        GenreAdapter genre = new GenreAdapter();
        String titles = req.getParameter("title");
        genre.setTitle(titles);
        String descriptions = req.getParameter("description");
        genre.setDescription(descriptions);
        Validator validator = new GenreValidator();
        ManagerDAO dao = new DBManagerGenre();
        if (!validator.exists(genre)) {
            dao.create(genre);
            RequestDispatcher dispatcher = req.getRequestDispatcher("genres");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("message", "Уже существует");
            req.setAttribute("entity", genre);
            doGet(req, resp);
        }
    }
}
