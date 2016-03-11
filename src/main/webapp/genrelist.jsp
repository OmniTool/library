<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
</head>
<body>
<h1>Библиотека</h1>
<h2>Список </h2>
<button formaction="">Добавить</button>
<br><br>

<c:forEach var="genre" items="${genreList}">
    <tr>
        <td><c:out value="${genre}"/></td>
    </tr>
</c:forEach>

<c:out value=
               "${SessionObject}" escapeXml="false" />

<p><c:out value='${requestScope.test}'/></p>
<p><c:out value='${request.test}'/></p>
<c:out value='${pageContext.test}' escapeXml='false' />
<p><c:out value="${requestScope.test}"/></p>
<p><c:out value="${request.test}"/></p>
<p><c:out value="${test}"/></p>
<p><c:out value="${sessionScope.test}"/></p>


<%= request.getAttribute("genreList")%>
<%= request.getAttribute("test")%>

<p>
    <a href="">Пункт 1</a>
    <button formaction="">Удалить</button>
    <button formaction="">Редактировать</button>
</p>


</body>
</html>