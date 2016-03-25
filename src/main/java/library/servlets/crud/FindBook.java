package library.servlets.crud;

import library.dataAccess.accessPoint.DAO;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBookAuthor;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findbook")
public class FindBook extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        String ids = req.getParameter("id");
        if (ids == null) {
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("books");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);
            if (id != 0) {
                DAO dao = new DAO();
                DBManagerBookAuthor subDao = new DBManagerBookAuthor();
                BookAdapter entity = dao.getEntityByIdBook(id);
                entity.setAuthorsList(subDao.searchAuthorsByBook(entity));
                RequestDispatcher dispatcher = req.getRequestDispatcher("bookinfo.jsp");
                req.setAttribute("entity", entity);
                req.setAttribute("bread", "<a href=\"/books\">Книги</a>");
                req.setAttribute("genre", dao.getEntityByIdGenre(entity.getGenereId()));
                req.setAttribute("ref", "/findauthor?id=");
                req.setAttribute("refGenre", "/findgenre?id=");
                dispatcher.forward(req, resp);
            }
        }
    }
}
