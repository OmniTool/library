package library.servlets.test;


import library.hibernate.dao.BaseDAO;
import library.hibernate.dao.impl.DAOAuthor;
import library.hibernate.dao.impl.DAOBook;
import library.hibernate.entities.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/testHibernateAuthor")
public class TestHibernateAuthor extends HttpServlet {

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

        Author entity = new Author();

        BaseDAO dao = new DAOAuthor();
        BaseDAO daoBook = new DAOBook();

        List<Author> list = null;


            //create
        Book book1 = (Book) daoBook.getEntityById(20);
        Book book2 = (Book) daoBook.getEntityById(22);

        entity.setFirstName("test author fname");
        entity.setMiddleName("test author mname");
        entity.setSecondName("test author sname");
        entity.setBiography("test bio");
        entity.setBirthYear(1111);
        entity.setBooksList(new ArrayList<BookAuthor>());
        entity.getBooksList().add(new BookAuthor(book1, entity));
        entity.getBooksList().add(new BookAuthor(book2, entity));

        out.print("<br> List:");
        out.print("getId = " + entity.getId() + "<br>");
        out.print("getFirstName = " + entity.getFirstName() + "<br>");
        out.print("getMiddleName = " + entity.getMiddleName() + "<br>");
        out.print("getSecondName = " + entity.getSecondName() + "<br>");
        out.print("getBirthYear = " + entity.getBirthYear() + "<br>");
        out.print("getBiography = " + entity.getBiography() + "<br>");
        out.print("getBooksList = " + entity.getBooksList() + "<br>");


            int futureId = dao.create(entity);

        out.print("<br> Add:");
        list = dao.getAll();
        for (Author e : list)
            out.print("<p>" + e.getFirstName() + " " + e.getSecondName() + " " + e.getBirthYear() + " " + e.getBooksList() + "</p>");

            //read
            Author entity1 = (Author) dao.getEntityById(futureId);
            out.print("<br> Read:");
        out.print("getId = " + entity1.getId() + "<br>");
        out.print("getFirstName = " + entity1.getFirstName() + "<br>");
        out.print("getMiddleName = " + entity1.getMiddleName() + "<br>");
        out.print("getSecondName = " + entity1.getSecondName() + "<br>");
        out.print("getBirthYear = " + entity1.getBirthYear() + "<br>");
        out.print("getBiography = " + entity1.getBiography() + "<br>");
        out.print("getBooksList = " + entity1.getBooksList() + "<br>");

        out.print("<br> List:");
        list = dao.getAll();
        for (Author e : list)
            out.print("<p>" + e.getFirstName() + " " + e.getSecondName() + " " + e.getBirthYear() + " " + e.getBooksList() + "</p>");

            //update
        Book book3 = (Book) daoBook.getEntityById(21);

        entity1.setFirstName("new test author fname");
        entity1.setMiddleName("new test author mname");
        entity1.setSecondName("new test author sname");
        entity1.setBiography("new test bio");
        entity1.setBirthYear(2222);
        entity1.setBooksList(new ArrayList<BookAuthor>());
        entity1.getBooksList().add(new BookAuthor(book3, entity1));

            dao.update(entity1);

            Author entity2 = (Author) dao.getEntityById(futureId);
            out.print("<br> Update:");
        out.print("getId = " + entity2.getId() + "<br>");
        out.print("getFirstName = " + entity2.getFirstName() + "<br>");
        out.print("getMiddleName = " + entity2.getMiddleName() + "<br>");
        out.print("getSecondName = " + entity2.getSecondName() + "<br>");
        out.print("getBirthYear = " + entity2.getBirthYear() + "<br>");
        out.print("getBiography = " + entity2.getBiography() + "<br>");
        out.print("getBooksList = " + entity2.getBooksList() + "<br>");

        out.print("<br> List:");
        list = dao.getAll();
        for (Author e : list)
            out.print("<p>" + e.getFirstName() + " " + e.getSecondName() + " " + e.getBirthYear() + " " + e.getBooksList() + "</p>");

            //delete
            dao.delete(entity2);


        out.print("<br> List:");
        list = dao.getAll();
        for (Author e : list)
            out.print("<p>" + e.getFirstName() + " " + e.getSecondName() + " " + e.getBirthYear() + " " + e.getBooksList() + "</p>");



        out.print("<form> <p><button formaction=\"index.jsp\">&lt;&lt;&lt;</button></p> </form>");

        out.print("</body>");
        out.print("</html>");

        out.close();

    }
}
