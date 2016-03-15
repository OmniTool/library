package library.servlets.crud;

import library.dao.DBManagerBook;
import library.dao.DBManagerBook;
import library.dao.DBManagerBookAuthor;
import library.dao.ManagerDAO;
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

@WebServlet("/findbook")
public class FindBook extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        RequestDispatcher dispatcher = req.getRequestDispatcher("findbook.jsp");
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
            ManagerDAO dao = new DBManagerBook();
            DBManagerBookAuthor subDao = new DBManagerBookAuthor();
            try {
                Book book = (Book) dao.getEntityById(id);
                book.setAuthorsList(subDao.searchAuthorsByBook(book));
//                out.print("Find: " + id + "<br>");
//                out.print("id = " + book.getId() + "<br>");
//                out.print("title = " + book.getTitle() + "<br>");
//                out.print("pubYear = " + book.getPubYear() + "<br>");
//                out.print("genereId = " + book.getGenereId() + "<br>");
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
