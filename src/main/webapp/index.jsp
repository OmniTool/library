<%@ page import="library.crud.managers.ManagerDBGenres" %>
<%@ page import="library.objects.Genre" %>
<html>
<body>
<h2>Hello World!</h2>

<%
    ManagerDBGenres managerDB = new ManagerDBGenres();

    Genre genre = new Genre();
    genre.setTitle("genre 2");
    genre.setDescription("descr 2");

    managerDB.add(genre);
    managerDB.selectAll();
    managerDB.selectSearchTitle("2");
    managerDB.selectSearchTitle("tit");

    Genre genreSearched = new Genre();
    genreSearched.setId(1);
    managerDB.selectSpecialId(genreSearched);

    managerDB.selectAll();

    Genre genreDeleted = new Genre();
    genreDeleted.setId(39);
    managerDB.delete(genreDeleted);

    managerDB.selectAll();



%>

</body>
</html>
