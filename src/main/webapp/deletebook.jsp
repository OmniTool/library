<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        <%@include file='css/style.css' %>
    </style>
</head>
<body>

<h1>Удаление книги</h1>

<form method="POST">
    <p><input placeholder="ID" name="id"></p>
    <p><button formaction="removebook">Удалить</button></p>
</form>

</body>
</html>
