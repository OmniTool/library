package library.servlets.crud.active;

import library.dataAccess.adapters.hibernate.dao.impl.DBManagerAuthor;
import library.dataAccess.adapters.hibernate.dao.ManagerDAO;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBookAuthor;
import library.dataAccess.adapters.hibernate.entities.Author;
import library.dataAccess.adapters.hibernate.entities.Book;
import library.dataAccess.validators.impl.AuthorValidator;
import library.dataAccess.validators.Validator;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addauthor")
public class AddAuthor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ManagerDAO dao = new DBManagerBook();
        try {
            List<Author> list = dao.getAll();
            req.setAttribute("sourceListBook", list);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("addauthor.jsp");
        req.setAttribute("bread", "<a href=\"/authors\">Авторы</a>");
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Author author = new Author();
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
        String[] arrBooks = req.getParameterValues("listBook");
            List<Integer> selectedIds = new ArrayList<>();
        if (arrBooks != null) {
            for (String s : arrBooks) {
                int id = Integer.parseInt(s);
                selectedIds.add(id);
            }
        }
        Validator validator = new AuthorValidator();
        ManagerDAO daoAuthor = new DBManagerAuthor();
        ManagerDAO daoBook = new DBManagerBook();
//        DBManagerBookAuthor daoBookAuthor = new DBManagerBookAuthor();
            try {
                List<Book> listB = new ArrayList<>();
                for (int id : selectedIds) {
                    listB.add((Book) daoBook.getEntityById(id));
                }
                author.setBooksList(listB);
                if (!validator.exists(author)) {
//                    int futureId =
                            daoAuthor.create(author);
//                    //jdbc
//                    for (int id : selectedIds) {
//                        BookAuthor ba = new BookAuthor();
//                        ba.setAuthorId(futureId);
//                        ba.setBookId(id);
//                        daoBookAuthor.create(ba);
//                    }
                    RequestDispatcher dispatcher = req.getRequestDispatcher("authors");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("message", "Уже существует");
                    req.setAttribute("entity", author);
                    req.setAttribute("currentListBook", listB);
                    doGet(req, resp);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            }

    }
}
