package library.servlets.crud;

import library.dao.DBManagerBook;
import library.dao.DBManagerBook;
import library.dao.ManagerDAO;
import library.dao.entities.Book;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/books")
public class ShowAllBooks extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ManagerDAO dao = new DBManagerBook();
        try {
            List<Book> list = dao.getAll();
            req.setAttribute("list", list);
            req.setAttribute("pageName", "Книги");
            req.setAttribute("action", "addbook");
            req.setAttribute("ref", "/findbook?id=");

            RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
            req.setAttribute("pageName", "Книги");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            System.out.println(e.getMessage());//TODO отправить на страницу с ошибкой
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }

    }


}
