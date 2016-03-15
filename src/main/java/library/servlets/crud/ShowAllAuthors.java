package library.servlets.crud;

import library.dao.DBManagerAuthor;
import library.dao.DBManagerAuthor;
import library.dao.ManagerDAO;
import library.dao.entities.Author;

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

@WebServlet("/authors")
public class ShowAllAuthors extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ManagerDAO dao = new DBManagerAuthor();
        try {
            List<Author> list = dao.getAll();
            req.setAttribute("list", list);
            req.setAttribute("pageName", "Авторы");

            RequestDispatcher dispatcher = req.getRequestDispatcher("authorlist.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            System.out.println(e.getMessage());//TODO отправить на страницу с ошибкой
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }
    }


}
