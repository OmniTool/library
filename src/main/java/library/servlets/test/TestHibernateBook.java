package library.servlets.test;


import library.dataAccess.connectors.hibernate.dao.BaseDAO;
import library.dataAccess.connectors.hibernate.dao.impl.DAOAuthor;
import library.dataAccess.connectors.hibernate.dao.impl.DAOBook;
import library.dataAccess.connectors.hibernate.dao.impl.DAOGenre;
import library.dataAccess.connectors.hibernate.entities.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/testHibernateBook")
public class TestHibernateBook extends HttpServlet {

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

        BookHiber entity = new BookHiber();

        BaseDAO dao = new DAOBook();
        BaseDAO daoAuthor = new DAOAuthor();
        BaseDAO daoGenre = new DAOGenre();

        List<BookHiber> list = null;


        //create
        AuthorHiber author1 = (AuthorHiber) daoAuthor.getEntityById(23);
        AuthorHiber author2 = (AuthorHiber) daoAuthor.getEntityById(24);

        entity.setTitle("test book title");
        entity.setPubYear(1234);
        entity.setGenre((GenreHiber)daoGenre.getEntityById(70));
        entity.setAuthorsList(new ArrayList<BookAuthorHiber>());
        entity.getAuthorsList().add(new BookAuthorHiber(entity, author1));
        entity.getAuthorsList().add(new BookAuthorHiber(entity, author2));

        out.print("<br> List:");
        out.print("getId = " + entity.getId() + "<br>");
        out.print("getFirstName = " + entity + "<br>");
        out.print("getBirthYear = " + entity.getAuthorsList() + "<br>");


        int futureId = dao.create(entity);

        out.print("<br> Add:");
        list = dao.getAll();
        for (BookHiber e : list)
            out.print("<p>" + e + "</p>");

        //read
        BookHiber entity1 = (BookHiber) dao.getEntityById(futureId);
        out.print("<br> Read:");
        out.print("<br> List:");
        out.print("getId = " + entity1 + "<br>");
        out.print("getBirthYear = " + entity1.getAuthorsList() + "<br>");
        out.print("<br> List:");
        list = dao.getAll();
        for (BookHiber e : list)
            out.print("<p>" + e + "</p>");

        //update
        AuthorHiber author3 = (AuthorHiber) daoAuthor.getEntityById(21);

        entity1.setTitle("new test book title");
        entity1.setPubYear(4321);
        entity1.setGenre((GenreHiber) daoGenre.getEntityById(69));
        entity1.getAuthorsList().clear();
        entity1.getAuthorsList().add(new BookAuthorHiber(entity1, author3));


        dao.update(entity1);

        BookHiber entity2 = (BookHiber) dao.getEntityById(futureId);
        out.print("<br> Update:");
        out.print("<br> List:");
        out.print("getId = " + entity1 + "<br>");
        out.print("getAuthorsList = " + entity1.getAuthorsList() + "<br>");

        out.print("<br> List:");
        list = dao.getAll();
        for (BookHiber e : list)
            out.print("<p>" + e + "</p>");

        //delete
        dao.delete(entity2);

        out.print("<br> Delete:");
        out.print("<br> List:");
        list = dao.getAll();
        for (BookHiber e : list)
            out.print("<p>" + e + "</p>");



        out.print("<form> <p><button formaction=\"index.jsp\">&lt;&lt;&lt;</button></p> </form>");

        out.print("</body>");
        out.print("</html>");

        out.close();

    }
}
