<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        <%@include file='css/style.css' %>
    </style>
</head>
<body>

<h1>Добавление автора</h1>

<form method="POST">
    <p><input type="text" value="" maxlength="64" placeholder="Фамилия" name="secondName" required pattern="^[a-zA-Zа-яА-Я \-]+$" title="Введите фамилию"></p>
    <p><input type="text" value="" maxlength="64" placeholder="Имя" name="firstName" required pattern="^[a-zA-Zа-яА-Я \-]+$" title="Введите имя"></p>
    <p><input type="text" value="" maxlength="64" placeholder="Отчество" name="middleName" pattern="^[a-zA-Zа-яА-Я \-]+$" title="Введите отчество"></p>
    <p><input type="text" value="" placeholder="Год рождения" name="birthYear" pattern="-?\d{4}" title="Введите год в формате ГГГГ"></p>
    <p><textarea name="biography" value="" placeholder="Биография"></textarea></p>
    <p><button formaction="addauthor">Добавить</button></p>
</form>

</body>
</html>
