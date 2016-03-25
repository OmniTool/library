package library.servlets.crud;

import library.dataAccess.accessPoint.DAO;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBookAuthor;
import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findauthor")
public class FindAuthor extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        String ids = req.getParameter("id");
        if (ids == null) {
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("authors");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);
            if (id != 0) {
                DAO dao = new DAO();
                DBManagerBookAuthor subDao = new DBManagerBookAuthor();
                AuthorAdapter entity = dao.getEntityByIdAuthor(id);
                entity.setBooksList(subDao.searchBooksByAuthor(entity));
                RequestDispatcher dispatcher = req.getRequestDispatcher("authorinfo.jsp");
                req.setAttribute("entity", entity);
                req.setAttribute("bread", "<a href=\"/authors\">Авторы</a>");
                req.setAttribute("ref", "/findbook?id=");
                dispatcher.forward(req, resp);
            }
        }
    }
}
