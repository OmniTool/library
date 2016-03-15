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
<h2>Книги</h2>


<form>
    <p><button formaction="showallbooks">Показать каталог</button></p>
</form>

<form>
    <p><button formaction="addbook">Добавить книгу</button></p>
</form>

<%--<form >--%>
    <%--<p><button formaction="findbook">Найти книгу по ID</button></p>--%>
<%--</form>--%>

<form >
    <p><button formaction="editbook">Изменить книгу</button></p>
</form>

<form >
    <p><button formaction="removebook">Удалить книгу</button></p>
</form>

<form >
    <p><button formaction="findbookbyname">Найти книгу по заглавию</button></p>
</form>

</body>
</html>
