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
        <p><h1 class="centred leftspace"><a href="/index.jsp">Библиотека</a></h1></p>
        <p><h2 class="centred">${bread} > ${pageName}</h2></p>
    </div>
</div>

<div class="parent big_topspace">
    <p class="message">${message}</p>

    <p>
    <div class="block">
        <form method="POST" class="centred">
            <p><input type="text" value="${entity.title}" maxlength="64" placeholder="Название жанра" name="title" required pattern=".*\S.*" title="Введите название жанра"></p>
            <p><textarea name="description" placeholder="Описание жанра">${entity.description}</textarea></p>
            <p><button formaction="addgenre">Добавить</button></p>
        </form>
    </div>
    </p>


</div>



</body>
</html>










