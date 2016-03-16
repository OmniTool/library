<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <style>
        <%@include file='css/style.css' %>
    </style>
</head>
<body>

<div class="parent topspace">
    <div class="block">
        <p><h1 class="centred"><a href="/index.jsp">Библиотека</a></h1></p>
        <p><h2 class="centred">${bread} > <a href="/findbook?id=${entity.id}">${entity.title}</a> > Редактирование</h2></p>
    </div>
</div>

<div class="parent big_topspace">

    <p>
    <div class="block">
        <form method="POST" class="centred">
            <p><input value="${entity.id}" hidden name="id"></p>
            <p><input type="text" name="title" value="entity.title" maxlength="64" placeholder="Название жанра" required pattern=".*" title="Введите название жанра"></p>
            <p><textarea name="description" value="entity.description" placeholder="Описание жанра"></textarea></p>
            <p><button formaction="addgenre">Добавить</button></p>
        </form>
    </div>
    </p>


</div>

</body>
</html>








<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title></title>--%>
    <%--<style>--%>
        <%--<%@include file='css/style.css' %>--%>
    <%--</style>--%>
<%--</head>--%>
<%--<body>--%>

<%--<h1>Редактирование жанра</h1>--%>

<%--<form method="POST">--%>
    <%--<p><input placeholder="ID" name="id"></p>--%>
    <%--<p><input type="text" value="" maxlength="64" placeholder="Название жанра" name="title" required pattern=".*" title="Введите название жанра"></p>--%>
    <%--<p><textarea name="description" value="" placeholder="Описание жанра"></textarea></p>--%>
    <%--<p><button formaction="editgenre">Изменить</button></p>--%>
<%--</form>--%>

<%--</body>--%>
<%--</html>--%>
