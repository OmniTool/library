package library.servlets.crud;

import library.dao.*;
import library.dao.DBManagerAuthor;
import library.dao.entities.Author;
import library.dao.entities.Book;

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

@WebServlet("/findauthor")
public class FindAuthor extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        RequestDispatcher dispatcher = req.getRequestDispatcher("findauthor.jsp");
//        req.setAttribute("pageName", "");
//        dispatcher.forward(req, resp);
//    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");
//        PrintWriter out = resp.getWriter();
//
//        out.print("<!DOCTYPE html>");
//        out.print("<html>");
//        out.print("<body>");
//        out.print("<h1></h1>");

        int id = 0;

        String ids = req.getParameter("id");

            id = Integer.parseInt(ids);



        boolean isValid = true;

        if (isValid && id != 0) {
            ManagerDAO dao = new DBManagerAuthor();
            DBManagerBookAuthor subDao = new DBManagerBookAuthor();

            //ManagerDAO daoTest = new DBManagerBook();
            try {
                Author author = (Author) dao.getEntityById(id);
                author.setBooksList(subDao.searchBooksByAuthor(author));
                //author.setBooksList(daoTest.getAll());

                RequestDispatcher dispatcher = req.getRequestDispatcher("authorinfo.jsp");
                req.setAttribute("entity", author);
                req.setAttribute("itemName", author.getFirstName() + author.getSecondName() + author.getMiddleName());
                req.setAttribute("pageName", "<a href=\"/authors\">Авторы</a>");
                req.setAttribute("list", author.getBooksList());
                dispatcher.forward(req, resp);

//                out.print("Find: " + id + "<br>");
//                out.print("secondName = " + author.getSecondName() + "<br>");
//                out.print("firstName = " + author.getFirstName() + "<br>");
//                out.print("middleName = " + author.getMiddleName() + "<br>");
//                out.print("birthYear = " + author.getBirthYear() + "<br>");
//                out.print("biography = " + author.getBiography() + "<br>");
            } catch (SQLException e) {
                System.out.println(e.getMessage());//TODO отправить на страницу с ошибкой
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            }

//            out.print("<form> <p><button formaction=\"index.jsp\">&lt;&lt;&lt;</button></p> </form>");
//
//            out.print("</body>");
//            out.print("</html>");
//
//            out.close();
        }
    }
}
