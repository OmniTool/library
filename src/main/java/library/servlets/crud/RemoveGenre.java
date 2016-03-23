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

@WebServlet("/removegenre")
public class RemoveGenre extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = 0;
        String ids = req.getParameter("id");

        if (ids == null) { //TODO проверка на число
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("genres");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);

            Genre genre = new Genre();

            genre.setId(id);

            Validator validator = new GenreValidator();

            //if (validator.canBeDeleted(genre)) {
                ManagerDAO dao = new DBManagerGenre();
                try {
                    dao.delete(genre);

                    RequestDispatcher dispatcher = req.getRequestDispatcher("genres");
                    dispatcher.forward(req, resp);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());//TODO отправить на страницу с ошибкой
                } catch (NamingException e) {
                    System.out.println(e.getMessage());
                }

        }
    }
}
