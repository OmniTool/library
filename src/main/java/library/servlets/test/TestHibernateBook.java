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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<body>");
        out.print("<h1></h1>");

        Book entity = new Book();

        BaseDAO dao = new DAOBook();
        BaseDAO daoAuthor = new DAOAuthor();
        BaseDAO daoGenre = new DAOGenre();

        List<Book> list = null;

        //create
        Author author1 = (Author) daoAuthor.getEntityById(23);
        Author author2 = (Author) daoAuthor.getEntityById(24);

        entity.setTitle("test book title");
        entity.setPubYear(1234);
        entity.setGenre((Genre)daoGenre.getEntityById(70));
        entity.setAuthorsList(new ArrayList<BookAuthor>());
        entity.getAuthorsList().add(new BookAuthor(entity, author1));
        entity.getAuthorsList().add(new BookAuthor(entity, author2));

        out.print("<br> List:");
        out.print("getId = " + entity.getId() + "<br>");
        out.print("getFirstName = " + entity + "<br>");
        out.print("getBirthYear = " + entity.getAuthorsList() + "<br>");


        int futureId = dao.create(entity);

        out.print("<br> Add:");
        list = dao.getAll();
        for (Book e : list)
            out.print("<p>" + e + "</p>");

        //read
        Book entity1 = (Book) dao.getEntityById(futureId);
        out.print("<br> Read:");
        out.print("<br> List:");
        out.print("getId = " + entity1 + "<br>");
        out.print("getBirthYear = " + entity1.getAuthorsList() + "<br>");
        out.print("<br> List:");
        list = dao.getAll();
        for (Book e : list)
            out.print("<p>" + e + "</p>");

        //update
        Author author3 = (Author) daoAuthor.getEntityById(21);

        entity1.setTitle("new test book title");
        entity1.setPubYear(4321);
        entity1.setGenre((Genre) daoGenre.getEntityById(69));
        entity1.getAuthorsList().clear();
        entity1.getAuthorsList().add(new BookAuthor(entity1, author3));


        dao.update(entity1);

        Book entity2 = (Book) dao.getEntityById(futureId);
        out.print("<br> Update:");
        out.print("<br> List:");
        out.print("getId = " + entity1 + "<br>");
        out.print("getAuthorsList = " + entity1.getAuthorsList() + "<br>");

        out.print("<br> List:");
        list = dao.getAll();
        for (Book e : list)
            out.print("<p>" + e + "</p>");

        //delete
        dao.delete(entity2);

        out.print("<br> Delete:");
        out.print("<br> List:");
        list = dao.getAll();
        for (Book e : list)
            out.print("<p>" + e + "</p>");



        out.print("<form> <p><button formaction=\"index.jsp\">&lt;&lt;&lt;</button></p> </form>");

        out.print("</body>");
        out.print("</html>");

        out.close();

    }
}
