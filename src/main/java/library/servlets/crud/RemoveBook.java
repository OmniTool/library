package library.servlets.crud;

import library.dataAccess.accessPoint.DAO;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removebook")
public class RemoveBook extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        String ids = req.getParameter("id");
        if (ids == null) {
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("books");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);
            BookAdapter book = new BookAdapter();
            book.setId(id);
            DAO dao = new DAO();
            dao.delete(book);
            RequestDispatcher dispatcher = req.getRequestDispatcher("books");
            dispatcher.forward(req, resp);
        }
    }
}
