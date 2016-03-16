package library.servlets.crud;

import library.dao.DBManagerAuthor;
import library.dao.DBManagerAuthor;
import library.dao.ManagerDAO;
import library.dao.entities.Author;
import library.utils.validation.AuthorValidator;
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

@WebServlet("/removeauthor")
public class RemoveAuthor extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = 0;
        String ids = req.getParameter("id");

        if (ids == null) { //TODO проверка на число
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("authors");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);

            Author author = new Author();

            author.setId(id);

            Validator validator = new AuthorValidator();

            if (validator.canBeDeleted(author)) {
                ManagerDAO dao = new DBManagerAuthor();
                try {
                    dao.delete(author);

                    RequestDispatcher dispatcher = req.getRequestDispatcher("authors");
                    dispatcher.forward(req, resp);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());//TODO отправить на страницу с ошибкой
                } catch (NamingException e) {
                    System.out.println(e.getMessage());
                }


            }
        }
    }
}
