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

@WebServlet("/editbook")
public class EditBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("editbook.jsp");
        req.setAttribute("pageName", "");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<body>");
        out.print("<h1></h1>");

        Book book = new Book();

        String ids = req.getParameter("id");

            book.setId(Integer.parseInt(ids));

        String titles = req.getParameter("title");

            book.setTitle(titles);

        String pubYears = req.getParameter("pubYear");

            book.setPubYear(Integer.parseInt(pubYears));

        String genereIds = req.getParameter("genereId");

            book.setGenereId(Integer.parseInt(genereIds));


        Validator validator = new BookValidator();

        if (validator.canBeUpdated(book)) {
            ManagerDAO dao = new DBManagerBook();
            try {
                dao.update(book);

                out.print("Edit:<br>");
                out.print("id = " + book.getId() + "<br>");
                out.print("title = " + book.getTitle() + "<br>");
                out.print("pubYear = " + book.getPubYear() + "<br>");
                out.print("genereId = " + book.getGenereId() + "<br>");
            } catch (SQLException e) {
                out.print("<p>SQLException caught: " + e.getMessage() + "</p>");
            } catch (NamingException e) {
                out.print("<p>NamingException caught: " + e.getMessage() + "</p>");

            }

            out.print("<form> <p><button formaction=\"index.jsp\">&lt;&lt;&lt;</button></p> </form>");

            out.print("</body>");
            out.print("</html>");

            out.close();
        }
    }
}
