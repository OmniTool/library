package library.servlets.crud;

import library.dao.DBManagerBook;
import library.dao.DBManagerBook;
import library.dao.ManagerDAO;
import library.dao.entities.Book;
import library.utils.validation.BookValidator;
import library.utils.validation.Validator;

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

@WebServlet("/removebook")
public class RemoveBook extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        RequestDispatcher dispatcher = req.getRequestDispatcher("deletebook.jsp");
//        req.setAttribute("pageName", "");
//        dispatcher.forward(req, resp);
//    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Book book = new Book();

        String ids = req.getParameter("id");

            book.setId(Integer.parseInt(ids));

        Validator validator = new BookValidator();

        if (validator.canBeDeleted(book)) {
            ManagerDAO dao = new DBManagerBook();
            try {
                dao.delete(book);

                RequestDispatcher dispatcher = req.getRequestDispatcher("books");
                dispatcher.forward(req, resp);

            } catch (SQLException e) {
                System.out.println(e.getMessage());//TODO отправить на страницу с ошибкой
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            }


        }
    }
}
