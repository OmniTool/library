package library.servlets.crud;

import library.dao.DBManagerGenre;
import library.dao.ManagerDAO;
import library.dao.entities.Genre;

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
import java.util.Enumeration;

@WebServlet("/editgenre")
public class EditGenre extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("editgenre.jsp");
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

        Genre genre = new Genre();

        String[] ids = req.getParameterValues("id");
        if (ids.length != 0) {
            genre.setId(Integer.parseInt(ids[0])); //���������...... �����?
        }

        String[] titles = req.getParameterValues("title");
        if (titles.length != 0) {
            genre.setTitle(titles[0]);
        }

        String[] descriptions = req.getParameterValues("description");
        if (descriptions.length != 0) {
            genre.setDescription(descriptions[0]);
        }

        //���������.............
        boolean isValid = true;

        if (isValid) {
            ManagerDAO dao = new DBManagerGenre();
            try {
                dao.update(genre);

                out.print("Edit:<br>");
                out.print("id = " + genre.getId() + "<br>");
                out.print("title = " + genre.getTitle() + "<br>");
                out.print("description = " + genre.getDescription() + "<br>");
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
