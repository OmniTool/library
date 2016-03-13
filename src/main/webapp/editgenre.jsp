<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<h1>Редактирование жанра</h1>

<form method="POST">
    <p><input placeholder="ID" name="id"></p>
    <p><input placeholder="Новое название жанра" name="title" required></p>
    <p><input placeholder="Новое описание жанра" name="description"></p>
    <p><button formaction="editgenre">Изменить</button></p>
</form>

</body>
</html>
