package library.servlets.crud;

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
import java.util.Iterator;
import java.util.List;

@WebServlet("/findauthorbyname")
public class FindAuthorByName extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("findauthorbyname.jsp");
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

        String secondName = null;
        String firstName = null;
        String middleName = null;

        String secondNames = req.getParameter("secondName");

            secondName = secondNames;

        String firstNames = req.getParameter("firstName");

            firstName = firstNames;

        String middleNames = req.getParameter("middleName");

            middleName = middleNames;



        boolean isValid = true;

        if (isValid && (secondName != null || firstName != null || middleName != null)) { //��������
            ManagerDAO dao = new DBManagerAuthor();
            int count = 0;

            try {
                List<Author> list = dao.getAll();

                for(Iterator<Author> iter = list.iterator(); iter.hasNext();){
                    Author current = iter.next();
                    if (!current.getSecondName().toUpperCase().contains(secondName.toUpperCase())) {
                        iter.remove();
                    }
                }

                for(Iterator<Author> iter = list.iterator(); iter.hasNext();){
                    Author current = iter.next();
                    if (!current.getFirstName().toUpperCase().contains(firstName.toUpperCase())) {
                        iter.remove();
                    }
                }

                for(Iterator<Author> iter = list.iterator(); iter.hasNext();){
                    Author current = iter.next();
                    if (!current.getMiddleName().toUpperCase().contains(middleName.toUpperCase())) {
                        iter.remove();
                    }
                }

                for (Author author : list) {
                    out.print("<p>" + author + "</p>");
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
}
