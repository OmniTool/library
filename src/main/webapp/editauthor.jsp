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
        <p><h2 class="centred"><span class="marked">${bread}</span> > <a href="/findbook?id=${entity.id}">${entity.firstName} ${entity.middleName} ${entity.secondName}</a> > Редактирование</h2></p>
    </div>
</div>

<div class="parent big_topspace">
    <p class="message">${message}<p>

    <p>
    <div class="block">
        <form method="POST" class="centred">
            <p><input value="${entity.id}" hidden name="id"></p>
            <p><input type="text" value="${entity.secondName}" maxlength="64" placeholder="Фамилия" name="secondName" required pattern=".*\S.*" title="Введите фамилию"></p>
            <p><input type="text" value="${entity.firstName}" maxlength="64" placeholder="Имя" name="firstName" required pattern=".*\S.*" title="Введите имя"></p>
            <p><input type="text" value="${entity.middleName}" maxlength="64" placeholder="Отчество" name="middleName" pattern=".*\S.*" title="Введите отчество"></p>
            <p><input type="text" value="${entity.birthYear}" placeholder="Год рождения" name="birthYear" pattern="-?\d{4}" title="Введите год в формате ГГГГ" required></p>
            <p><textarea name="biography" placeholder="Биография">${entity.biography}</textarea></p>
            <p><button formaction="editauthor">Изменить</button></p>
        </form>
    </div>
    </p>

    <p>
    <div class="block">
        <form id="resultForm">
            <p><select size="5" name="result" class="listMulticatch" multiple>
                <option disabled>Выбранные книги</option>


            </select></p>
        </form>
    </div>

    <div class="block">
        <p><button form="sourceForm" onclick="dataSelectAdd()"><<</button></p>
        <p><button form="resultForm" onclick="dataSelectDelete()">>></button></p>
    </div>

    <div class="block">
        <form id="sourceForm">
            <p><select size="5" name="source" class="listMulticatch" multiple>
                <option disabled>Выберите книги</option>
                <c:forEach var="item" items="${sourceListBook}">
                    <option value="${item.id}">${item.title}</option>
                </c:forEach>
            </select></p>
        </form>
    </div>

    </p>


</div>

<script src="js/twoselect.js">
//    function dataSelectAdd() {
//        var form1 = document.forms["sourceForm"];
//        var selectedItem = form1.elements.source;
//
//        for (var i = 0; i < selectedItem.options.length; i++) {
//            var option = selectedItem.options[i];
//            if(option.selected) {
//                alert( option.value );
//            }
//        }
//
////        var option = new Option("Текст", "value", true, true);
//
//        var form2 = document.forms["resultForm"];
//
//        var resultSelect = form2.elements.result;
//        resultSelect.add(selectedItem);
//
//
//    }
//    function dataSelectDelete() {
//        var form2 = document.forms["resultForm"];
//        var select = form2.elements.result;
//        for (var i = 0; i < select.options.length; i++) {
//            var option = select.options[i];
//            if(option.selected) {
//                alert( option.value );
//            }
//        }
//    }
</script>

</body>
</html>









