package library.servlets.test;

import library.dataAccess.hibernate.dao.BaseDAO;
import library.dataAccess.hibernate.dao.impl.DAOGenre;
import library.dataAccess.hibernate.entities.*;

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

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        doPost(req, resp);
//    }

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
            out.print("getId = " + entity.getId() + "<br>");
            out.print("getTitle = " + entity.getTitle() + "<br>");
            out.print("getDescription = " + entity.getDescription() + "<br>");

            int futureId = dao.create(entity);

        out.print("<br> List:");
        list = dao.getAll();
        for (GenreHiber e : list)
            out.print("<p>" + e.getTitle() + " " + e.getDescription() + "</p>");

            //read
            GenreHiber entity1 = (GenreHiber) dao.getEntityById(futureId);
            out.print("<br> Read:");
            out.print("getId = " + entity1.getId() + "<br>");
            out.print("getTitle = " + entity1.getTitle() + "<br>");
            out.print("getDescription = " + entity1.getDescription() + "<br>");

        out.print("<br> List:");
        list = dao.getAll();
        for (GenreHiber e : list)
            out.print("<p>" + e.getTitle() + " " + e.getDescription() + "</p>");

            //update
            entity1.setTitle("new test title");
            entity1.setDescription("new test descr");
            dao.update(entity1);

            GenreHiber entity2 = (GenreHiber) dao.getEntityById(futureId);
            out.print("<br> Update:");
            out.print("getId = " + entity2.getId() + "<br>");
            out.print("getTitle = " + entity2.getTitle() + "<br>");
            out.print("getDescription = " + entity2.getDescription() + "<br>");

        out.print("<br> List:");
        list = dao.getAll();
        for (GenreHiber e : list)
            out.print("<p>" + e.getTitle() + " " + e.getDescription() + "</p>");

            //delete
            dao.delete(entity2);


        out.print("<br> List:");
        list = dao.getAll();
        for (GenreHiber e : list)
            out.print("<p>" + e.getTitle() + " " + e.getDescription() + "</p>");



        out.print("<form> <p><button formaction=\"index.jsp\">&lt;&lt;&lt;</button></p> </form>");

        out.print("</body>");
        out.print("</html>");

        out.close();

    }
}
