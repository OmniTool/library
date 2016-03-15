<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

        <title></title>
        <style>
            <%@include file='css/style.css' %>
        </style>
</head>
<body>
<h1>Библиотека</h1>
<h2>${pageName}</h2>
<button formaction="">Добавить</button>
<br><br>

<c:forEach var="item" items="${list}">
    <p>
        <a href="">${item.secondName} ${item.firstName} ${item.middleName}</a>
        <button formaction="">Удалить</button>
        <button formaction="">Редактировать</button>
    </p>
</c:forEach>

</body>
</html>