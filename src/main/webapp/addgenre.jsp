<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<h1>Добавление жанра</h1>

<form method="POST">

    <p><input type="text" value="" maxlength="64" placeholder="Название жанра" name="title" required pattern=".*" title="Введите название жанра"></p>
    <p><input type="text" value="" placeholder="Описание жанра" name="description"></p>
    <p><button formaction="addgenre">Добавить</button></p>
</form>

</body>
</html>
