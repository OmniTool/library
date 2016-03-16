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
        <p><h2 class="centred">${bread} > ${pageName}</h2></p>
    </div>
</div>

<div class="parent big_topspace">

    <p>
    <div class="block">
        <form method="POST" class="centred">
            <p><input type="text" value="" maxlength="64" placeholder="Название жанра" name="title" required pattern="\S*" title="Введите название жанра"></p>
            <p><textarea name="description" value="" placeholder="Описание жанра"></textarea></p>
            <p><button formaction="addgenre">Добавить</button></p>
        </form>
    </div>
    </p>


</div>



</body>
</html>










