package library.servlets.crud;

import library.dataAccess.accessPoint.active.hibernate.dao.impl.DBManagerAuthor;
import library.dataAccess.accessPoint.active.hibernate.dao.ManagerDAO;
import library.dataAccess.accessPoint.active.hibernate.dao.impl.DBManagerBookAuthor;
import library.dataAccess.accessPoint.active.hibernate.entities.Author;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/findauthor")
public class FindAuthor extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = 0;
        String ids = req.getParameter("id");

        if (ids == null) { //TODO проверка на число
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("authors");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);

            boolean isValid = true;

            if (isValid && id != 0) {
                ManagerDAO dao = new DBManagerAuthor();
                DBManagerBookAuthor subDao = new DBManagerBookAuthor();

                try {
                    Author entity = (Author) dao.getEntityById(id);

                    entity.setBooksList(subDao.searchBooksByAuthor(entity));

                    RequestDispatcher dispatcher = req.getRequestDispatcher("authorinfo.jsp");
                    req.setAttribute("entity", entity);
                    //req.setAttribute("pageName", entity.getFirstName() + entity.getSecondName() + entity.getMiddleName());
                    req.setAttribute("bread", "<a href=\"/authors\">Авторы</a>");
                    //req.setAttribute("list", entity.getBooksList());
                    req.setAttribute("ref", "/findbook?id=");
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
