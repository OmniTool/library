<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <style>
        <%@include file='css/style.css' %>
    </style>
</head>
<body>
<h1>Библиотека</h1>
<h2>Жанры</h2>


<form>
    <p><button formaction="showallgenres">Показать каталог</button></p>
</form>

<form>
    <p><button formaction="addgenre">Добавить жанр</button></p>
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


</body>
</html>
