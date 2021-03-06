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
import java.util.List;

@WebServlet("/genres")
public class ShowAllGenres extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAO dao = new DAO();
        List<GenreAdapter> list = dao.getAllGenre();
        req.setAttribute("list", list);
        req.setAttribute("pageName", "Жанры");
        req.setAttribute("action", "addgenre");
        req.setAttribute("actionSearch", "findgenrebyname");
        req.setAttribute("ref", "/findgenre?id=");
        RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
        dispatcher.forward(req, resp);
    }
}
