package library.servlets.crud;

import library.dao.DBManagerGenre;
import library.dao.ManagerDAO;
import library.dao.entities.Genre;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/showallgenres")
public class ShowAllGenres extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<head>"
        + "<title>Библиотека</title>"
        + "</head>");
        out.print("<body>");
        out.print("<h1>Genres</h1>");

        ManagerDAO dao = new DBManagerGenre();
        try {
            List<Genre> list = dao.getAll();
            for (Genre genre : list) {
                out.print("<p>" + genre + "</p>");
            }
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
