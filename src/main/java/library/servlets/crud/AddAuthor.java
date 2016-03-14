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

@WebServlet("/addauthor")
public class AddAuthor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("addauthor.jsp");
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

        Author author = new Author();

        String secondNames = req.getParameter("secondName"); // поменять

            author.setSecondName(secondNames);

        String firstNames = req.getParameter("firstName");

            author.setFirstName(firstNames);

        String middleNames = req.getParameter("middleName");

            author.setMiddleName(middleNames);

        String birthYears = req.getParameter("birthYear");

            author.setBirthYear(Integer.parseInt(birthYears));

        String biographys = req.getParameter("biography");

            author.setBiography(biographys);



        boolean isValid = true;

        if (isValid) {
            ManagerDAO dao = new DBManagerAuthor();
            try {
                dao.create(author);

                out.print("Add:<br>");
                out.print("secondName = " + author.getSecondName() + "<br>");
                out.print("firstName = " + author.getFirstName() + "<br>");
                out.print("middleName = " + author.getMiddleName() + "<br>");
                out.print("birthYear = " + author.getBirthYear() + "<br>");
                out.print("biography = " + author.getBiography() + "<br>");

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
