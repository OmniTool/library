<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
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
        <p><h2 class="centred">${bread} > ${entity.title}</h2></p>
    </div>
</div>
<div class="parent big_topspace">
    <p>
    <form method="GET">
        <p><input TYPE="button" VALUE="Изменить"
                  onclick="window.location.href='/editgenre?id=${entity.id}'"></p>
    </form>
    <form method="GET">
        <p><input TYPE="button" VALUE="Удалить"
                  onclick="window.location.href='/removegenre?id=${entity.id}'"></p>
    </form>
    </p>
    <p>
    <div class="block">
        <p class="message">${message}</p>
        <p class="centred">
            <c:forEach var="item" items="${listBooks}">
        <p class="centred">
            <a href="${ref}${item.id}" class="content">${item.title}</a>
        </p>
        </c:forEach>
    </p>
</div>
</p>
    <p>
        <div class="block">
            <p><h3 class="centred">Описание</h3></p>
            <p class="centred content">${entity.description}</p>
        </div>
    </p>
</div>
</body>
</html>