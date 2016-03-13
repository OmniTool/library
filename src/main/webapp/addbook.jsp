<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<h1>Добавление книги</h1>

<form method="POST">
    <p><input placeholder="Название книги" name="title" required></p>
    <p><input placeholder="Год публикации" name="pubYear"></p>
    <p><input placeholder="Жанр id" name="genereId"></p>
    <p><button formaction="addbook">Добавить</button></p>
</form>

</body>
</html>
