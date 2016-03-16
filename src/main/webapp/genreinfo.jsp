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
        <p><h1 class="centred"><a href="/index.jsp">Библиотека</a></h1></p>
        <p><h2 class="centred">${bread} > ${pageName}</h2></p>
    </div>
</div>

<div class="parent big_topspace">

    <p>
        <div class="block">
    <p ><h3 class="centred">Описание</h3></p>
    <p class="centred content">${entity.description}</p>

</div>
</p>



</div>



</body>
</html>