package library.servlets.crud;

import library.dataAccess.accessPoint.DAO;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.adapters.hibernate.dao.ManagerDAO;
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
import java.util.List;

@WebServlet("/books")
public class ShowAllBooks extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAO dao = new DAO();
        try {
            List<Book> list = dao.getAllBook();
            req.setAttribute("list", list);
            req.setAttribute("pageName", "Книги");
            req.setAttribute("action", "addbook");
            req.setAttribute("actionSearch", "findbookbyname");
            req.setAttribute("ref", "/findbook?id=");
            RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }
    }
}
