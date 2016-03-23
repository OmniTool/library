package library.servlets.test;

import library.dataAccess.accessPoint.dao.impl.DBManagerBookAuthor;
import library.dataAccess.accessPoint.dao.ManagerDAO;
import library.dataAccess.accessPoint.entities.BookAuthor;

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

@WebServlet("/testDBManagerBookAuthor")
public class TestBooksAuthors extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req, resp);
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

        BookAuthor book_author = new BookAuthor();       

            ManagerDAO dao = new DBManagerBookAuthor();
            try {

                //create
                book_author.setBookId(17);
                book_author.setAuthorId(14);

                out.print("<br> Add:");
                out.print("getId = " + book_author.getId() + "<br>");
                out.print("getBookId = " + book_author.getBookId() + "<br>");
                out.print("getAuthorId = " + book_author.getAuthorId() + "<br>");

                dao.create(book_author);

                //read
                BookAuthor bookAuthor1 = (BookAuthor) dao.getEntityById(1);
                out.print("<br> Read:");
                out.print("getId = " + bookAuthor1.getId() + "<br>");
                out.print("getBookId = " + bookAuthor1.getBookId() + "<br>");
                out.print("getAuthorId = " + bookAuthor1.getAuthorId() + "<br>");

                //update
                bookAuthor1.setBookId(18);
                bookAuthor1.setAuthorId(15);
                dao.update(bookAuthor1);

                BookAuthor bookAuthor2 = (BookAuthor) dao.getEntityById(1);
                out.print("<br> Update:");
                out.print("getId = " + bookAuthor2.getId() + "<br>");
                out.print("getBookId = " + bookAuthor2.getBookId() + "<br>");
                out.print("getAuthorId = " + bookAuthor2.getAuthorId() + "<br>");

                //delete
                dao.delete(bookAuthor2);


                out.print("<br> Delete:");
                List<BookAuthor> list = dao.getAll();
                for (BookAuthor ba : list)
                    out.print("<p>" + ba + "</p>");
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
