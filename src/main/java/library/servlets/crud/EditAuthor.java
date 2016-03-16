package library.servlets.crud;

import library.dao.*;
import library.dao.DBManagerAuthor;
import library.dao.entities.Author;
import library.dao.entities.Book;
import library.dao.entities.Genre;
import library.utils.validation.AuthorValidator;
import library.utils.validation.GenreValidator;
import library.utils.validation.Validator;

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

@WebServlet("/editauthor")
public class EditAuthor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = 0;
        String ids = req.getParameter("id");

        if (ids == null) { //TODO проверка на число
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("authors");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);

            boolean isValid = true;

            if (isValid && id != 0) {
                ManagerDAO daoAuthor = new DBManagerAuthor();
                ManagerDAO daoBook = new DBManagerBook();
                DBManagerBookAuthor subDao = new DBManagerBookAuthor();

                try {
                    Author entity = (Author) daoAuthor.getEntityById(id);
                    Validator validator = new AuthorValidator();
                    validator.trim(entity);

                    entity.setBooksList(subDao.searchBooksByAuthor(entity));

                    List<Book> listBook = daoBook.getAll();
                    req.setAttribute("sourceListBook", listBook);

                    RequestDispatcher dispatcher = req.getRequestDispatcher("editauthor.jsp");
                    req.setAttribute("entity", entity);
                    //req.setAttribute("pageName", entity.getFirstName() + entity.getSecondName() + entity.getMiddleName());
                    req.setAttribute("bread", "<a href=\"/authors\">Авторы</a>");
                    //req.setAttribute("list", entity.getBooksList());
                    //req.setAttribute("ref", "/findbook?id=");
                    dispatcher.forward(req, resp);

                } catch (SQLException e) {
                    System.out.println(e.getMessage());//TODO отправить на страницу с ошибкой
                } catch (NamingException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Author author = new Author();

        String ids = req.getParameter("id");

            author.setId(Integer.parseInt(ids));

        String secondNames = req.getParameter("secondName");
        
            author.setSecondName(secondNames);
        
        String firstNames = req.getParameter("firstName");
        
            author.setFirstName(firstNames);
        
        String middleNames = req.getParameter("middleName");
        
            author.setMiddleName(middleNames);
        
        String birthYears = req.getParameter("birthYear");
        
            author.setBirthYear(Integer.parseInt(birthYears));
        
        String biographys = req.getParameter("biography");
        
            author.setBiography(biographys);

        Validator validator = new AuthorValidator();
        validator.trim(author);


        if (validator.canBeUpdated(author)) {}

            ManagerDAO dao = new DBManagerAuthor();
            try {
                dao.update(author);

                RequestDispatcher dispatcher = req.getRequestDispatcher("findauthor?id=" + author.getId());
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            }

    }
}
