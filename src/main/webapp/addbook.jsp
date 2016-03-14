<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        <%@include file='css/style.css' %>
    </style>
</head>
<body>
<br>
<br>
<div class="parent">
    <p>
    <div class="block">
        <form method="POST">
            <p><input type="text" value="" maxlength="64" placeholder="Название книги" name="title" required pattern=".*" title="Введите название книги"></p>
            <p><input type="text" value="" placeholder="Год публикации" name="pubYear" pattern="-?\d{4}" title="Введите год в формате ГГГГ"></p>
            <p><input placeholder="Жанр id" name="genereId"></p>
            <p><button formaction="addbook">Добавить</button></p>
        </form>
    </div>
    </p>
    <p>
    <div class="block">
        <form id="resultForm">
            <p><select size="5" name="result" class="listMulticatch">
                <c:forEach var="item" items="${resultList}">
                    <%--<option value="${item.key}">${item.value}</option>--%>
                </c:forEach>
            </select>
        </form>
    </div>

    <div class="block">
        <p><button form="sourceForm" onclick="dataSelectAdd(this.form)"><<</button></p>
        <p><button form="resultForm" onclick="dataSelectDelete(this.form)">>></button></p>
    </div>

    <div class="block">
        <form id="sourceForm">
            <p><select size="5" name="source" class="listMulticatch">
                <option>Выберите автора</option>
                <c:forEach var="item" items="${sourceList}">
                    <option value="${item.id}">${item.secondName} ${item.firstName} ${item.middleName}</option>
                </c:forEach>
            </select>
        </form>
    </div>

    </p>
</div>




<script>
    function dataSelectAdd(form) {
        var target = form.source.selectedIndex
        var forAdd;
        if(target) alert("Выбран автор c id: " + form.source.options[target].value)
        if(target) forAdd = form.source.options[target].value;

    }
    //    document.getElementById('id')
</script>

</body>
</html>
