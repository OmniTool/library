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
            a.button {
                -webkit-appearance: button;
                -moz-appearance: button;
                appearance: button;

                text-decoration: none;
                color: initial;
            }
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
    <form method="GET">
        <p><input TYPE="button" VALUE="Изменить"
                  onclick="window.location.href='/editbook?id=${entity.id}'"></p>
    </form>

    <form method="GET">
        <p><input TYPE="button" VALUE="Удалить"
                  onclick="window.location.href='/removebook?id=${entity.id}'"></p>
    </form>
    </p>




    <p>
    <div class="block">
        <p ><h3 class="centred">Год публикации</h3></p>
    <p class="centred">${entity.pubYear}</p>
    <p class="topspace"><h3 class="centred">Жанр</h3></p>
    <p class="centred"><a href="${refGenre}${genre.id}" class="content">${genre.title}</a></p>
    <p class="topspace"><h3 class="centred">Авторы</h3></p>
    <p class="centred">
        <c:forEach var="item" items="${entity.authorsList}">
    <p class="centred">
        <a href="${ref}${item.id}" class="content">${item.firstName} ${item.middleName} ${item.secondName}</a>
    </p>
    </c:forEach>
    </p>
</div>
</p>



</div>



</body>
</html>