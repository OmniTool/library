<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        <%@include file='css/style.css' %>
    </style>
</head>
<body>

<h1>Поиск жанра по id</h1>

<form method="POST">
    <p><input placeholder="ID" name="id"></p>
    <p><button formaction="findgenre">Найти</button></p>
</form>

</body>
</html>
