package library.servlets.crud;

import library.dataAccess.adapters.hibernate.dao.impl.DBManagerGenre;
import library.dataAccess.adapters.hibernate.dao.ManagerDAO;
import library.dataAccess.adapters.hibernate.entities.Genre;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/findgenre")
public class FindGenre extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//

        int id = 0;
        String ids = req.getParameter("id");


        if (ids == null) {
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("genres");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);

            boolean isValid = true;

            if (isValid && id != 0) {
                ManagerDAO dao = new DBManagerGenre();
                try {
                    Genre entity = (Genre) dao.getEntityById(id);

                    RequestDispatcher dispatcher = req.getRequestDispatcher("genreinfo.jsp");
                    req.setAttribute("entity", entity);
                    //req.setAttribute("pageName", entity.getTitle());
                    req.setAttribute("bread", "<a href=\"/genres\">Жанры</a>");
                    dispatcher.forward(req, resp);
//
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                } catch (NamingException e) {
                    System.out.println(e.getMessage());
                }//
            }
        }
    }
}
