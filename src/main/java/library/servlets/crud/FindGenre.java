package library.servlets.crud;

import library.dataAccess.accessPoint.DAO;
import library.dataAccess.adapters.hibernate.entities.GenreAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findgenre")
public class FindGenre extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        String ids = req.getParameter("id");
        if (ids == null) {
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("genres");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);
            if (id != 0) {
                DAO dao = new DAO();
                GenreAdapter entity = dao.getEntityByIdGenre(id);
                RequestDispatcher dispatcher = req.getRequestDispatcher("genreinfo.jsp");
                req.setAttribute("entity", entity);
                req.setAttribute("bread", "<a href=\"/genres\">Жанры</a>");
                dispatcher.forward(req, resp);
            }
        }
    }
}
