package library.servlets.crud;

import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.adapters.hibernate.dao.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.Book;
import library.dataAccess.validators.impl.BookValidator;
import library.dataAccess.validators.Validator;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/removebook")
public class RemoveBook extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = 0;
        String ids = req.getParameter("id");

        if (ids == null) { //TODO проверка на число
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("books");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);

            Book book = new Book();

            book.setId(id);

            Validator validator = new BookValidator();

            //if (validator.canBeDeleted(book)) {
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
