<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title></title>--%>
    <%--<style>--%>
        <%--<%@include file='css/style.css' %>--%>
    <%--</style>--%>
<%--</head>--%>
<%--<body>--%>

<%--<h1>${pageName} > ${entity.title}</h1>--%>

<%--<form method="POST">--%>
    <%--<p><input value="" hidden name="id"></p>--%>
    <%--<p><input type="text" value="" maxlength="64" placeholder="Название книги" name="title" required pattern=".*" title="Введите название книги"></p>--%>
    <%--<p><input type="text" value="" placeholder="Год публикации" name="pubYear" pattern="-?\d{4}" title="Введите год в формате ГГГГ" required></p>--%>
    <%--<p><input placeholder="Жанр id" name="genereId"></p>--%>
    <%--<p><button formaction="editbook">Изменить</button></p>--%>

<%--</form>--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <style>
        <%@include file='css/style.css' %>
    </style>
    <style>
        <%@include file='css/bootstrap.min.css' %>
    </style>
</head>
<body>

<div class="parent topspace">
    <div class="block">
        <p><h1 class="centred"><a href="/index.jsp">Библиотека</a></h1></p>
        <p><h2 class="centred">${bread} > ${pageName}</h2></p>
    </div>
</div>

<div class="parent big_topspace">
    <p><h2>Добавление книги</h2></p>
    <p>
    <div class="block">
        <form method="POST">
            <p><input value="${entity.id}" hidden name="id"></p>
            <p><input type="text" value="${entity.title}" maxlength="64" placeholder="Название книги" name="title" required pattern=".*" title="Введите название книги"></p>
            <p><input type="text" value="${entity.pubYear}" placeholder="Год публикации" name="pubYear" pattern="-?\d{4}" title="Введите год в формате ГГГГ" required></p>
            <%--<p><input placeholder="Жанр id" name="genereId" required></p>--%>

            <p><select class="listMulticatch" size="1" name="genereId">
                <option disabled>Жанр</option>
                <c:set var="targetId" scope="session" value="${entity.id}"/>
                <c:forEach var="genre" items="${sourceListGenre}">
                    <p>
                    <c:set var="targetId" scope="session" value="${genre.id}"/>

                    <c:if test="${targetId=entityId}">
                        <p>My salary is: <c:out value="${salary}"/><p>
                    </c:if>

                    </p>
                </c:forEach>

            </select></p>

            <%--<p class="topspace"><h3 class="centred">Жанр</h3></p>--%>
            <%--<p class="centred"><a href="${refGenre}${genre.id}" class="content">${genre.title}</a></p>--%>

            <p><button formaction="editbook">Добавить</button></p>
        </form>
    </div>
    </p>
    </div>

</body>
</html>
