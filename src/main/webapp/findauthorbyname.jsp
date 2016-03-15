<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        <%@include file='css/style.css' %>
    </style>
</head>
<body>

<h1>Поиск автора по ФИО</h1>

<form method="POST">
    <p><input type="text" value="" maxlength="64" placeholder="Фамилия" name="secondName" pattern="^[a-zA-Zа-яА-Я \-]+$" title="Введите фамилию"></p>
    <p><input type="text" value="" maxlength="64" placeholder="Имя" name="firstName" pattern="^[a-zA-Zа-яА-Я \-]+$" title="Введите имя"></p>
    <p><input type="text" value="" maxlength="64" placeholder="Отчество" name="middleName" pattern="^[a-zA-Zа-яА-Я \-]+$" title="Введите отчество"></p>
    <p><button formaction="findauthorbyname">Найти</button></p>
</form>

</body>
</html>
