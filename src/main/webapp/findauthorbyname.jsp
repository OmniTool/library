<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<h1>Поиск автора по ФИО</h1>

<form method="POST">
    <p><input placeholder="Фамилия" name="secondName"></p>
    <p><input placeholder="Имя" name="firstName"></p>
    <p><input placeholder="Отчество" name="middleName"></p>
    <p><button formaction="findauthorbyname">Найти</button></p>
</form>

</body>
</html>
