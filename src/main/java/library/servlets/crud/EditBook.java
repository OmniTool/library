package library.servlets.crud;

import library.dataAccess.accessPoint.active.hibernate.dao.impl.DBManagerAuthor;
import library.dataAccess.accessPoint.active.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.accessPoint.active.hibernate.dao.ManagerDAO;
import library.dataAccess.accessPoint.active.hibernate.dao.impl.DBManagerBookAuthor;
import library.dataAccess.accessPoint.active.hibernate.dao.impl.DBManagerGenre;
import library.dataAccess.accessPoint.active.hibernate.entities.Author;
import library.dataAccess.accessPoint.active.hibernate.entities.Book;
import library.dataAccess.accessPoint.active.hibernate.entities.BookAuthor;
import library.dataAccess.accessPoint.active.hibernate.entities.Genre;
import library.dataAccess.jdbc.validators.impl.BookValidator;
import library.dataAccess.jdbc.validators.Validator;

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
import java.util.Iterator;
import java.util.List;

@WebServlet("/editbook")
public class EditBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = 0;
        String ids = req.getParameter("id");

        if (ids == null) {
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("books");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);
            ManagerDAO daoBook = new DBManagerBook();
            DBManagerBookAuthor daoBookAuthor = new DBManagerBookAuthor();
            ManagerDAO daoGenre = new DBManagerGenre();
            ManagerDAO daoAuthor = new DBManagerAuthor();
            try {
                List<Author> listAuthor = daoAuthor.getAll();
                req.setAttribute("sourceListAuthor", listAuthor);
                List<Genre> listGenre = daoGenre.getAll();
                req.setAttribute("sourceListGenre", listGenre);
                RequestDispatcher dispatcher = req.getRequestDispatcher("editbook.jsp");
                if (req.getAttribute("entity") == null) {
                    Book entity = (Book) daoBook.getEntityById(id);
                    Validator validator = new BookValidator();
                    validator.trim(entity);
                    entity.setAuthorsList(daoBookAuthor.searchAuthorsByBook(entity));
                    req.setAttribute("entity", entity);
                    req.setAttribute("genre", daoGenre.getEntityById(entity.getGenereId()));
                }
                req.setAttribute("bread", "<a href=\"/books\">Книги</a>");
                req.setAttribute("ref", "/findauthor?id=");
                req.setAttribute("refGenre", "/findgenre?id=");
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

        Book book = new Book();

        String ids = req.getParameter("id");
        book.setId(Integer.parseInt(ids));
        String titles = req.getParameter("title");
        book.setTitle(titles);
        String pubYears = req.getParameter("pubYear");
        book.setPubYear(Integer.parseInt(pubYears));
        String genereIds = req.getParameter("genereId");
        book.setGenereId(Integer.parseInt(genereIds));
        String[] arrAuthors = req.getParameterValues("listAuthor");
        List<Integer> selectedIds = new ArrayList<>();
        List<Integer> forDelListId = new ArrayList<>();
        List<Integer> forAddListId = new ArrayList<>();
        if (arrAuthors != null) {
            for (String s : arrAuthors) {
                int id = Integer.parseInt(s);
                forAddListId.add(id);
                selectedIds.add(id);
            }
        }
        Validator validator = new BookValidator();
        ManagerDAO daoAuthor = new DBManagerAuthor();
        ManagerDAO daoBook = new DBManagerBook();
        DBManagerBookAuthor daoBookAuthor = new DBManagerBookAuthor();
        try {
            boolean isValid;
            Book forUpdBook = (Book) daoBook.getEntityById(book.getId());
            validator.trim(forUpdBook);
            if (book.getTitle().equals(forUpdBook.getTitle())
                    && book.getPubYear() == forUpdBook.getPubYear()
                    && book.getGenereId() == forUpdBook.getGenereId()) {
                isValid = true;
            } else {
                isValid = !validator.exists(book);
            }
            if (isValid) {
                for (Author b : daoBookAuthor.searchAuthorsByBook(book)) {
                    forDelListId.add(b.getId());
                }
                Iterator<Integer> iteratorAddList = forAddListId.iterator();
                while (iteratorAddList.hasNext()) {
                    int idForAdd = iteratorAddList.next();
                    Iterator<Integer> iteratorDelList = forDelListId.iterator();
                    while (iteratorDelList.hasNext()) {
                        int idForDel = iteratorDelList.next();
                        if (idForAdd == idForDel) {
                            iteratorDelList.remove();
                            iteratorAddList.remove();
                        }
                    }
                }

                for (int id : forAddListId) {
                    BookAuthor ba = new BookAuthor();
                    ba.setBookId(book.getId());
                    ba.setAuthorId(id);
                    daoBookAuthor.create(ba);
                }

                for (int id : forDelListId) {
                    BookAuthor ba = new BookAuthor();
                    ba.setBookId(book.getId());
                    ba.setAuthorId(id);
                    BookAuthor baFromDB = daoBookAuthor.searchEntityByName(ba).get(0);
                    if (baFromDB != null)
                        daoBookAuthor.delete(baFromDB);
                }

                daoBook.update(book);
                RequestDispatcher dispatcher = req.getRequestDispatcher("findbook?id=" + book.getId());
                dispatcher.forward(req, resp);

            } else {
                List<Author> list = new ArrayList<>();
                for (int id : selectedIds) {
                    list.add((Author) daoAuthor.getEntityById(id));
                }
                book.setAuthorsList(list);
                req.setAttribute("message", "Уже существует");
                req.setAttribute("entity", book);

                doGet(req, resp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());//TODO отправить на страницу с ошибкой
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }

    }
}
