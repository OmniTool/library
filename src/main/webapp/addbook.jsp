<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<h1>Добавление книги</h1>

<form method="POST">

    <p><input type="text" value="" maxlength="64" placeholder="Название книги" name="title" required pattern=".*" title="Введите название книги"></p>
    <p><input type="text" value="" placeholder="Год публикации" name="pubYear" pattern="-?\d{4}" title="Введите год в формате ГГГГ"></p>
    <p><input placeholder="Жанр id" name="genereId"></p>
    <p><button formaction="addbook">Добавить</button></p>
</form>

</body>
</html>
