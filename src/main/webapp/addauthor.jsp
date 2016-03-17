<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <%--<meta charset="utf-8">--%>
    <title>${pageName}</title>
    <style>
        <%@include file='css/style.css' %>
        <%@include file='css/multi-select.css' %>
    </style>
        <script src="js/jquery-2.1.0.js" type="text/javascript"></script>
        <script src="js/jquery.multi-select.js" type="text/javascript"></script>
</head>
<body>

<div class="parent topspace">

    <div class="block">
        <p><h1 class="centred leftspace"><a href="/index.jsp">Библиотека</a></h1></p>
        <p><h2 class="centred"><span class="marked">${bread}</span> > ${pageName}</h2></p>
    </div>
</div>

<div class="parent big_topspace">

    <p class="message">${message}<p>

    <p>
        <div class="block">
            <form method="POST" class="centred">
                <p><input type="text" value="${entity.secondName}" maxlength="64" placeholder="Фамилия" name="secondName" required pattern=".*\S.*" title="Введите фамилию"></p>
                <p><input type="text" value="${entity.firstName}" maxlength="64" placeholder="Имя" name="firstName" required pattern=".*\S.*" title="Введите имя"></p>
                <p><input type="text" value="${entity.middleName}" maxlength="64" placeholder="Отчество" name="middleName" pattern=".*\S.*" title="Введите отчество"></p>
                <p><input type="text" value="${entity.birthYear}" placeholder="Год рождения" name="birthYear" pattern="-?\d{4}" title="Введите год в формате ГГГГ" required></p>
                <p><textarea name="biography" placeholder="Биография">${entity.biography}</textarea></p>

                <p><select id="my-select" size="5" name="listBook" class="listMulticatch" multiple>
                    <option disabled>Выберите книги</option>
                    <c:forEach var="item" items="${sourceListBook}">
                        <option value="${item.id}">${item.title}</option>
                    </c:forEach>
                </select></p>

                <p><button formaction="addauthor">Добавить</button></p>
            </form>
        </div>
    </p>

    <p>
    <%--<div class="block">--%>
        <%--<form id="resultForm">--%>
            <%--<p><select size="5" name="result" class="listMulticatch" multiple id="res">--%>
                <%--<option disabled>Выбранные книги</option>--%>


            <%--</select></p>--%>
        <%--</form>--%>
    <%--</div>--%>

    <%--<div class="block">--%>
        <%--<p><button form="sourceForm" onclick="dataSelectAdd()"><<</button></p>--%>
        <%--<p><button form="resultForm" onclick="dataSelectDelete()">>></button></p>--%>
    <%--</div>--%>

    <%--<div class="block">--%>
        <%--<form id="sourceForm">--%>
            <%--<p><select size="5" name="source" class="listMulticatch" multiple id="sou">--%>
                <%--<option disabled>Выберите книги</option>--%>
                <%--<c:forEach var="item" items="${sourceListBook}">--%>
                    <%--<option value="${item.id}">${item.title}</option>--%>
                <%--</c:forEach>--%>
            <%--</select></p>--%>
        <%--</form>--%>
    <%--</div>--%>

    </p>

    <p>

    <%--<div class="block">--%>
        <%--<form id="sourceForm">--%>
            <%--<p><select id="my-select" size="5" name="source" class="listMulticatch" multiple='multiple'>--%>
                <%--<option disabled>Выберите книги</option>--%>
                <%--<c:forEach var="item" items="${sourceListBook}">--%>
                    <%--<option value="${item.id}">${item.title}</option>--%>
                <%--</c:forEach>--%>
            <%--</select></p>--%>
        <%--</form>--%>
    <%--</div>--%>



        <%--<select id="my-select" multiple='multiple'>--%>
            <%--<option value='elem_1'>elem 1</option>--%>
            <%--<option value='elem_2'>elem 2</option>--%>
            <%--<option value='elem_3'>elem 3</option>--%>
            <%--<option value='elem_4'>elem 4</option>--%>
            <%--<option value='elem_100'>elem 100</option>--%>
        <%--</select>--%>


        <script type="text/javascript">
            $('#my-select').multiSelect();
        </script>

    </p>

</div>










</body>
</html>











