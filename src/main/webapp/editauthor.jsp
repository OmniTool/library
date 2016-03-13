<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<h1>Редактирование автора</h1>

<form method="POST">
    <p><input placeholder="ID" name="id"></p>
    <p><input placeholder="Фамилия" name="secondName" required></p>
    <p><input placeholder="Имя" name="firstName" required></p>
    <p><input placeholder="Отчество" name="middleName"></p>
    <p><input placeholder="Год рождения" name="birthYear"></p>
    <p><input placeholder="Биография" name="biography"></p>
    <p><button formaction="editauthor">Изменить</button></p>
</form>

</body>
</html>
