<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<h1>Поиск книги по заголовку</h1>

<form method="POST">
    <p><input type="text" value="" maxlength="64" placeholder="Название книги" name="title" pattern=".*" title="Введите название книги"></p>
    <p><button formaction="findbookbyname">Найти</button></p>
</form>

</body>
</html>
