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
import java.util.List;

@WebServlet("/genres")
public class ShowAllGenres extends HttpServlet {




    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ManagerDAO dao = new DBManagerGenre();
        try {
            List<Genre> list = dao.getAll();
            req.setAttribute("list", list);
            req.setAttribute("pageName", "Жанры");
            req.setAttribute("action", "addgenre");
            req.setAttribute("ref", "/findgenre?id=");


            RequestDispatcher dispatcher = req.getRequestDispatcher("genrelist.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            System.out.println(e.getMessage());//TODO отправить на страницу с ошибкой -->
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }


    }


}
