package library.servlets.crud;

import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBookAuthor;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerGenre;
import library.dataAccess.adapters.hibernate.entities.Book;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

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
            boolean isValid = true;
            if (isValid && id != 0) {
                ManagerDAO dao = new DBManagerBook();
                DBManagerBookAuthor subDao1 = new DBManagerBookAuthor();
                ManagerDAO subDao2 = new DBManagerGenre();
                try {
                    Book entity = (Book) dao.getEntityById(id);
                    entity.setAuthorsList(subDao1.searchAuthorsByBook(entity));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("bookinfo.jsp");
                    req.setAttribute("entity", entity);
                    req.setAttribute("bread", "<a href=\"/books\">Книги</a>");
                    req.setAttribute("genre", subDao2.getEntityById(entity.getGenereId()));
                    req.setAttribute("ref", "/findauthor?id=");
                    req.setAttribute("refGenre", "/findgenre?id=");
                    dispatcher.forward(req, resp);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                } catch (NamingException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
