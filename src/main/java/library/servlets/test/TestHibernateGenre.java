package library.servlets.test;

import library.dataAccess.connectors.hibernate.dao.BaseDAO;
import library.dataAccess.connectors.hibernate.dao.impl.DAOGenre;
import library.dataAccess.connectors.hibernate.entities.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/testHibernateGenre")
public class TestHibernateGenre extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<body>");
        out.print("<h1></h1>");

        GenreHiber entity = new GenreHiber();

        BaseDAO dao = new DAOGenre();

        List<GenreHiber> list = null;

            //create
            entity.setTitle("test genre");
            entity.setDescription("test descr");

            out.print("<br> Add:");
            out.print("getId = " + entity + "<br>");


            int futureId = dao.create(entity);

        out.print("<br> List:");
        list = dao.getAll();
        for (GenreHiber e : list)
            out.print("<p>" + e + "</p>");

            //read
            GenreHiber entity1 = (GenreHiber) dao.getEntityById(futureId);
            out.print("<br> Read:");
            out.print("getId = " + entity1 + "<br>");

        out.print("<br> List:");
        list = dao.getAll();
        for (GenreHiber e : list)
            out.print("<p>" + e + "</p>");

            //update
            entity1.setTitle("new test title");
            entity1.setDescription("new test descr");
            dao.update(entity1);

            GenreHiber entity2 = (GenreHiber) dao.getEntityById(futureId);
            out.print("<br> Update:");
            out.print("getId = " + entity2 + "<br>");

        out.print("<br> List:");
        list = dao.getAll();
        for (GenreHiber e : list)
            out.print("<p>" + e + "</p>");

            //delete
            dao.delete(entity2);

        out.print("<br> Delete:");
        out.print("<br> List:");
        list = dao.getAll();
        for (GenreHiber e : list)
            out.print("<p>" + e + "</p>");



        out.print("<form> <p><button formaction=\"index.jsp\">&lt;&lt;&lt;</button></p> </form>");

        out.print("</body>");
        out.print("</html>");

        out.close();

    }
}
