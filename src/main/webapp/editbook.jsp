<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<h1>Редактирование книги</h1>

<form method="POST">
    <p><input placeholder="ID" name="id"></p>
    <p><input placeholder="Название книги" name="title" required></p>
    <p><input placeholder="Год публикации" name="pubYear"></p>
    <p><input placeholder="Жанр id" name="genereId"></p>
    <p><button formaction="editbook">Изменить</button></p>
</form>

</body>
</html>
