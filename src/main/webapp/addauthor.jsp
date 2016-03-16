<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <%--<meta charset="utf-8">--%>
    <title>${pageName}</title>
    <style>
        <%@include file='css/style.css' %>
    </style>
    <style>

    </style>
</head>
<body>

<div class="parent topspace">
    <div class="block">
        <p><h1 class="centred"><a href="/index.jsp">Библиотека</a></h1></p>
        <p><h2 class="centred"><span class="marked">${bread}</span> > ${pageName}</h2></p>
    </div>
</div>

<div class="parent big_topspace">

    <p>
        <div class="block">
            <form method="POST" class="centred">
    <p><input type="text" value="" maxlength="64" placeholder="Фамилия" name="secondName" required pattern="^[a-zA-Zа-яА-Я \-]+$" title="Введите фамилию"></p>
    <p><input type="text" value="" maxlength="64" placeholder="Имя" name="firstName" required pattern="^[a-zA-Zа-яА-Я \-]+$" title="Введите имя"></p>
    <p><input type="text" value="" maxlength="64" placeholder="Отчество" name="middleName" pattern="^[a-zA-Zа-яА-Я \-]+$" title="Введите отчество"></p>
    <p><input type="text" value="" placeholder="Год рождения" name="birthYear" pattern="-?\d{4}" title="Введите год в формате ГГГГ" required></p>
    <p><textarea name="biography" value="" placeholder="Биография"></textarea></p>
    <p><button formaction="addauthor">Добавить</button></p>
    </form>
</div>
</p>


</div>



</body>
</html>











