<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>${pageName}</title>
    <style>
        <%@include file='css/style.css' %>
    </style>
</head>
<body>

<div class="parent topspace">
    <div class="block">
        <p><h1 class="centred leftspace"><a href="/index.jsp">Библиотека</a></h1></p>
        <p><h2 class="centred">${pageName}</h2></p>
    </div>
</div>

<div class="parent big_topspace">

    <p>
    <form>
        <p><button formaction="${action}">Добавить</button></p>
    </form>
    </p>

    <p class="topspace">
    <form>
        <p><input type="text" value="" maxlength="64" placeholder="Поиск фамилии" name="secondName" required pattern=".*\S.*" title="Введите фамилию"></p>
        <p><input type="text" value="" maxlength="64" placeholder="Поиск имени" name="firstName" required pattern=".*\S.*" title="Введите имя"></p>
        <p><input type="text" value="" maxlength="64" placeholder="Поиск отчества" name="middleName" pattern=".*\S.*" title="Введите отчество"></p>
        <p><button formaction="${actionSearch}">Найти</button></p>
    </form>
    </p>

    <p class="topspace">
        <c:forEach var="item" items="${list}">
    <p>
        <a href="${ref}${item.id}" class="content">${item.firstName} ${item.middleName} ${item.secondName}</a>
    </p>
    </c:forEach>
    </p>




</div>



</body>
</html>





