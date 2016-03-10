<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Библиотека</title>
</head>
<body>
<h1>Библиотека</h1>
<h2>Тест</h2>
<h3>Жанры</h3>

<form>
    <p><button formaction="showallgenres">Показать каталог</button></p>
</form>

<form>
    <p><button formaction="addgenre">Добавить жанр</button></p>
</form>

<form >
    <p><button formaction="findgenre">Найти жанр по ID</button></p>
</form>

<form >
    <p><button formaction="editgenre">Изменить жанр</button></p>
</form>

<form >
    <p><button formaction="removegenre">Удалить жанр</button></p>
</form>

</body>
</html>