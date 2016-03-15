<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        <%@include file='css/style.css' %>
    </style>
</head>
<body>

<h1>Поиск жанра по названию</h1>

<form method="POST">
    <p><input type="text" value="" maxlength="64" placeholder="Название жанра" name="title" pattern=".*" title="Введите название жанра"></p>
    <p><button formaction="findgenrebyname">Найти</button></p>
</form>

</body>
</html>
