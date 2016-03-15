<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        <%@include file='css/style.css' %>
    </style>
</head>
<body>

<h1>Редактирование книги</h1>

<form method="POST">
    <p><input placeholder="ID" name="id"></p>
    <p><input type="text" value="" maxlength="64" placeholder="Название книги" name="title" required pattern=".*" title="Введите название книги"></p>
    <p><input type="text" value="" placeholder="Год публикации" name="pubYear" pattern="-?\d{4}" title="Введите год в формате ГГГГ" required></p>
    <p><input placeholder="Жанр id" name="genereId"></p>
    <p><button formaction="editbook">Изменить</button></p>

</form>

</body>
</html>
