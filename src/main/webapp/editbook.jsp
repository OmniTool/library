<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
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
        <p><h1 class="centred"><a href="/index.jsp">Библиотека</a></h1></p>
        <p><h2 class="centred">${bread} > <a href="/findbook?id=${entity.id}">${entity.title}</a> > Редактирование</h2></p>
    </div>
</div>
<div class="parent big_topspace">
    <p class="message">${message}</p>
    <p>
    <div class="block">
        <form method="POST" class="centred">
            <p><input value="${entity.id}" hidden name="id"></p>
            <p><input type="text" value="${entity.title}" maxlength="64" placeholder="Название книги" name="title" required pattern=".*\S.*" title="Введите название книги"></p>
            <p><input type="text" value="${entity.pubYear}" placeholder="Год публикации" name="pubYear" pattern="-?\d{4}" title="Введите год в формате ГГГГ" required></p>
            <p><select class="listMulticatch" size="1" name="genereId">
                <option disabled>Жанр</option>
                <c:forEach var="opt" items="${sourceListGenre}">
                    <p>
                        <c:set var="optionId" scope="session" value="${opt.id}"/>
                        <c:set var="targetId" scope="session" value="${genre.id}"/>

                        <c:choose>
                            <c:when test="${optionId==targetId}">
                                <option selected value="${opt.id}">${opt.title}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${opt.id}">${opt.title}</option>
                            </c:otherwise>
                        </c:choose>
                    </p>
                </c:forEach>
            </select></p>
            <p><select id="my-select" size="5" name="listAuthor" class="listMulticatch" multiple>
                <option disabled>Выберете авторов</option>
                <c:forEach var="opt" items="${sourceListAuthor}">
                    <p>
                        <c:set var="optionId" scope="session" value="${opt.id}"/>
                        <c:set var="isSelected" scope="session" value='false'/>

                        <c:forEach var="selected" items="${currentListAuthor}" >
                            <c:set var="targetId" scope="session" value="${selected.id}"/>
                            <c:choose>
                                <c:when test="${optionId==targetId}">
                                    <c:set var="isSelected" scope="session" value='true'/>
                                </c:when>
                            </c:choose>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${isSelected=='true'}">
                                <option selected value="${opt.id}">${opt.firstName} ${opt.middleName} ${opt.secondName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${opt.id}">${opt.firstName} ${opt.middleName} ${opt.secondName}</option>
                            </c:otherwise>
                        </c:choose>
                    </p>
                </c:forEach>
            </select></p>
            <p><button formaction="editbook">Изменить</button></p>
        </form>
    </div>
</div>
<script>
    $('#my-select').multiSelect();
</script>
</body>
</html>
