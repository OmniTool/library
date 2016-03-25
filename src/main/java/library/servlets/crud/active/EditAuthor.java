package library.servlets.crud.active;

import library.dataAccess.adapters.hibernate.dao.impl.DBManagerAuthor;
import library.dataAccess.accessPoint.ManagerDAO;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBook;
import library.dataAccess.adapters.hibernate.dao.impl.DBManagerBookAuthor;
import library.dataAccess.adapters.hibernate.entities.AuthorAdapter;
import library.dataAccess.adapters.hibernate.entities.BookAdapter;
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

@WebServlet("/editauthor")
public class EditAuthor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        String ids = req.getParameter("id");

        if (ids == null) {
            RequestDispatcher dispatcher1 = req.getRequestDispatcher("authors");
            dispatcher1.forward(req, resp);
        } else {
            id = Integer.parseInt(ids);
            ManagerDAO daoAuthor = new DBManagerAuthor();
            ManagerDAO daoBook = new DBManagerBook();

//            DBManagerBookAuthor daoBookAuthor = new DBManagerBookAuthor();

            List<BookAdapter> listBook = daoBook.getAll();
            req.setAttribute("sourceListBook", listBook);
            RequestDispatcher dispatcher = req.getRequestDispatcher("editauthor.jsp");

            if (req.getAttribute("entity") == null) {
                AuthorAdapter entity = (AuthorAdapter) daoAuthor.getEntityById(id);
                Validator validator = new AuthorValidator();
                validator.trim(entity);

//                    //jdbc
//                    entity.setBooksList(daoBookAuthor.searchBooksByAuthor(entity));

                //hibernate
                req.setAttribute("currentListBook", entity.getBooksList());

                req.setAttribute("entity", entity);
            }
            req.setAttribute("bread", "<a href=\"/authors\">Авторы</a>");
            dispatcher.forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        //jdbc
//        Author author = new Author();
//        String ids = req.getParameter("id");
//        author.setId(Integer.parseInt(ids));

        //hibernate
        String ids = req.getParameter("id");
        AuthorAdapter author = new AuthorAdapter(Integer.parseInt(ids));

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

//        //jdbc
//        List<Integer> forDelListId = new ArrayList<>();
//        List<Integer> forAddListId = new ArrayList<>();
//        if (arrBooks != null) {
//            for (String s : arrBooks) {
//                int id = Integer.parseInt(s);
//                forAddListId.add(id);
//                selectedBooksId.add(id);
//            }
//        }

        Validator validator = new AuthorValidator();
        ManagerDAO daoAuthor = new DBManagerAuthor();
        ManagerDAO daoBook = new DBManagerBook();
//        DBManagerBookAuthor daoBookAuthor = new DBManagerBookAuthor();
        boolean isValid;
        AuthorAdapter forUpdAuthor = (AuthorAdapter) daoAuthor.getEntityById(author.getId());
        validator.trim(forUpdAuthor);
        if (author.getFirstName().equals(forUpdAuthor.getFirstName())
                && author.getMiddleName().equals(forUpdAuthor.getMiddleName())
                && author.getSecondName().equals(forUpdAuthor.getSecondName())
                && author.getBirthYear() == forUpdAuthor.getBirthYear()) {
            isValid = true;
        } else {
            isValid = !validator.exists(author);
        }

        List<BookAdapter> listB = new ArrayList<>();
        for (int id : selectedBooksId) {
            listB.add((BookAdapter) daoBook.getEntityById(id));
        }
        author.setBooksList(listB);

        if (isValid) {

//                    //jdbc
//                    for (Book b : daoBookAuthor.searchBooksByAuthor(author)) {
//                        forDelListId.add(b.getId());
//                    }
//                    Iterator<Integer> iteratorAddList = forAddListId.iterator();
//                    while (iteratorAddList.hasNext()) {
//                        int idForAdd = iteratorAddList.next();
//                        Iterator<Integer> iteratorDelList = forDelListId.iterator();
//                        while (iteratorDelList.hasNext()) {
//                            int idForDel = iteratorDelList.next();
//                            if (idForAdd==idForDel) {
//                                iteratorDelList.remove();
//                                iteratorAddList.remove();
//                            }
//                        }
//                    }
//                    for (int id : forAddListId) {
//                        BookAuthor ba = new BookAuthor();
//                        ba.setBookId(id);
//                        ba.setAuthorId(author.getId());
//                        daoBookAuthor.create(ba);
//                    }
//                    for (int id : forDelListId) {
//                        BookAuthor ba = new BookAuthor();
//                        ba.setBookId(id);
//                        ba.setAuthorId(author.getId());
//                        BookAuthor baFromDB = daoBookAuthor.searchEntityByName(ba).get(0);
//                        if (baFromDB != null)
//                            daoBookAuthor.delete(baFromDB);
//                    }

            daoAuthor.update(author);

            RequestDispatcher dispatcher = req.getRequestDispatcher("findauthor?id=" + author.getId());
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("message", "Уже существует");
            req.setAttribute("entity", author);
            req.setAttribute("currentListBook", listB);
            doGet(req, resp);
        }
    }
}
