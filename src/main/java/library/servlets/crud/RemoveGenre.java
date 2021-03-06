package library.servlets.crud;

import library.dataAccess.accessPoint.DAO;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;
import library.dataAccess.adapters.hibernate.entities.GenreAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            GenreAdapter genre = new GenreAdapter();
            genre.setId(id);
            DAO dao = new DAO();
            DBManagerBook daoBook = new DBManagerBook();
            List<BookAdapter> listBooks = daoBook.searchBooksByGenre(genre);
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
        }
    }
}
