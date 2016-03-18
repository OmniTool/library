package library.servlets.crud;

import library.dao.*;
import library.dao.DBManagerAuthor;
import library.dao.entities.Author;
import library.dao.entities.Book;
import library.dao.entities.BookAuthor;
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
import java.util.ArrayList;
import java.util.Iterator;
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

                ManagerDAO daoAuthor = new DBManagerAuthor();
                ManagerDAO daoBook = new DBManagerBook();
                DBManagerBookAuthor subDao = new DBManagerBookAuthor();

                try {

                    List<Book> listBook = daoBook.getAll();
                    req.setAttribute("sourceListBook", listBook);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("editauthor.jsp");

                    if (req.getAttribute("entity") == null) {
                        Author entity = (Author) daoAuthor.getEntityById(id);
                        Validator validator = new AuthorValidator();
                        validator.trim(entity);
                        entity.setBooksList(subDao.searchBooksByAuthor(entity));
                        req.setAttribute("entity", entity);
                    }


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
        String[] arrBooks = req.getParameterValues("listBook");
            List<Integer> selectedBooksId = new ArrayList<>();
            List<Integer> forAddListId = new ArrayList<>();
            //List<BookAuthor> forAddList = new ArrayList<>();
            for (String s : arrBooks) {
                int id = Integer.parseInt(s);
                forAddListId.add(id);
                selectedBooksId.add(id);
            }
            List<Integer> forDelListId = new ArrayList<>();
            //List<BookAuthor> forDelList = new ArrayList<>();

        Validator validator = new AuthorValidator();
        ManagerDAO daoAuthor = new DBManagerAuthor();
        ManagerDAO daoBook = new DBManagerBook();
        DBManagerBookAuthor daoBookAuthor = new DBManagerBookAuthor();

            try {

                boolean isValid;
                Author forUpdAuthor = (Author) daoAuthor.getEntityById(author.getId());
                validator.trim(forUpdAuthor);
                if (author.getFirstName().equals(forUpdAuthor.getFirstName())
                        && author.getMiddleName().equals(forUpdAuthor.getMiddleName())
                        && author.getSecondName().equals(forUpdAuthor.getSecondName())
                        && author.getBirthYear()==forUpdAuthor.getBirthYear()) {
                    isValid = true;
                } else {
                    isValid = !validator.exists(author);
                }

                if (isValid) {
                    for (Book b : daoBookAuthor.searchBooksByAuthor(author)) {
                        forDelListId.add(b.getId());
                    }

                    Iterator<Integer> iteratorAddList = forAddListId.iterator();
                    while (iteratorAddList.hasNext()) {
                        int idForAdd = iteratorAddList.next();
                        Iterator<Integer> iteratorDelList = forDelListId.iterator();
                        while (iteratorDelList.hasNext()) {
                            int idForDel = iteratorDelList.next();
                            if (idForAdd==idForDel) {
                                iteratorDelList.remove();
                                iteratorAddList.remove();
                            }
                        }
                    }

                    for (int id : forAddListId) {
                        BookAuthor ba = new BookAuthor();
                        ba.setBookId(id);
                        ba.setAuthorId(author.getId());
                        daoBookAuthor.create(ba);
                    }

                    for (int id : forDelListId) {
                        BookAuthor ba = new BookAuthor();
                        ba.setBookId(id);
                        ba.setAuthorId(author.getId());
                        BookAuthor baFromDB = daoBookAuthor.searchEntityByName(ba).get(0);
                        if (baFromDB != null)
                            daoBookAuthor.delete(baFromDB);
                    }

                    daoAuthor.update(author);

                    RequestDispatcher dispatcher = req.getRequestDispatcher("findauthor?id=" + author.getId());
                    dispatcher.forward(req, resp);
                } else {
                    List<Book> l = new ArrayList<>();
                    for (int id : selectedBooksId) {
                        l.add((Book) daoBook.getEntityById(id));
                    }
                    author.setBooksList(l);
                    req.setAttribute("message", "Уже существует");
                    req.setAttribute("entity", author);
                    doGet(req, resp);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            }

    }
}
