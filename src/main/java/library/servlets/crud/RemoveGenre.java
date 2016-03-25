package library.servlets.crud;

import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerGenre;
import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.Book;
import library.dataAccess.adapters.hibernate.entities.Genre;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/removegenre")
public class RemoveGenre extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        String ids = req.getParameter("id");
        if (ids == null) {
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("genres");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);
            Genre genre = new Genre();
            genre.setId(id);
            ManagerDAO dao = new DBManagerGenre();
            DBManagerBook daoBook = new DBManagerBook();
                try {
                    List<Book> listBooks = daoBook.searchBooksByGenre(genre);
                    if (listBooks.size() != 0) {
                        req.setAttribute("message", "Используется в книгах");
                        req.setAttribute("listBooks", listBooks);
                        req.setAttribute("ref", "/findbook?id=");
                        RequestDispatcher dispatcher = req.getRequestDispatcher("findgenre");
                        dispatcher.forward(req, resp);
                    } else {
                        dao.delete(genre);
                        RequestDispatcher dispatcher = req.getRequestDispatcher("genres");
                        dispatcher.forward(req, resp);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                } catch (NamingException e) {
                    System.out.println(e.getMessage());
                }
        }
    }
}
