<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

    <p>
        <div class="block">
    <p class="centred">год рождения</p>
    <p class="centred">${entity.birthYear}</p>
    <p class="centred topspace">биография</p>
    <p class="centred">${entity.biography}</p>
    <p class="centred topspace">книги</p>
    <p class="centred">
        <c:forEach var="item" items="${list}">
    <p>
        <a href="${ref}${item.id}" class="content">${item.title}</a>
    </p>
    </c:forEach>
    </p>
</div>
</p>



</div>



</body>
</html>