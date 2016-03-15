<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>${pageName}</title>
    <style>
        <%@include file='css/style.css' %>
    </style>
</head>
<body>

<div class="parent topspace">
    <div class="block">
        <p><h1 class="centred"><a href="/index.jsp">Библиотека</a></h1></p>
        <p><h2 class="centred">${pageName}</h2></p>
    </div>
</div>

<div class="parent big_topspace">

    <p>
    <form>
        <p><button formaction="addgenre">Добавить жанр</button></p>
    </form>
    </p>

    <p class="topspace">
        <c:forEach var="item" items="${list}">
    <p>
        <a href="" class="content">${item.title}</a>

    </p>
    </c:forEach>
    </p>


    <p class="topspace">
    <div class="block">
    <form>
        <p><button formaction="showallgenres">Показать каталог</button></p>
    </form>



    <%--<form >--%>
    <%--<p><button formaction="findgenre">Найти жанр по ID</button></p>--%>
    <%--</form>--%>

    <form >
        <p><button formaction="editgenre">Изменить жанр</button></p>
    </form>

    <form >
        <p><button formaction="removegenre">Удалить жанр</button></p>
    </form>

    <form >
        <p><button formaction="findgenrebyname">Найти жанр по названию</button></p>
    </form>

    </div>
    </p>

</div>



</body>
</html>