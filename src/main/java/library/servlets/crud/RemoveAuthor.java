package library.servlets.crud;

import library.dataAccess.accessPoint.DAO;
import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeauthor")
public class RemoveAuthor extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        String ids = req.getParameter("id");
        if (ids == null) {
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("authors");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);
            AuthorAdapter author = new AuthorAdapter();
            author.setId(id);
            DAO dao = new DAO();
            dao.delete(author);
            RequestDispatcher dispatcher = req.getRequestDispatcher("authors");
            dispatcher.forward(req, resp);
        }
    }
}
