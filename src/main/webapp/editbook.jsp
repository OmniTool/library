<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <style>
        <%@include file='css/style.css' %>
    </style>
</head>
<body>

<div class="parent topspace">
    <div class="block">
        <p><h1 class="centred"><a href="/index.jsp">Библиотека</a></h1></p>
        <p><h2 class="centred">${bread} > <a href="/findbook?id=${entity.id}">${entity.title}</a> > Редактирование</h2></p>
    </div>
</div>

<div class="parent big_topspace">
    <p class="message">${message}<p>

    <p>
    <div class="block">
        <form method="POST" class="centred">
            <p><input value="${entity.id}" hidden name="id"></p>
            <p><input type="text" value="${entity.title}" maxlength="64" placeholder="Название книги" name="title" required pattern=".*\S.*" title="Введите название книги"></p>
            <p><input type="text" value="${entity.pubYear}" placeholder="Год публикации" name="pubYear" pattern="-?\d{4}" title="Введите год в формате ГГГГ" required></p>
            <%--<p><input placeholder="Жанр id" name="genereId" required></p>--%>

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

            <p><button formaction="editbook">Изменить</button></p>
        </form>
    </div>
    </p>


    <p>
    <div class="block">
        <form id="resultForm">
            <p><select size="5" name="result" class="listMulticatch" multiple>
                <option disabled>Выбранные авторы</option>


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
                <option disabled>Выберите авторов</option>
                <c:forEach var="item" items="${sourceListAuthor}">
                    <option value="${item.id}">${item.secondName} ${item.firstName} ${item.middleName}</option>
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
//        var resultSelect = form2.elements.result;
//        resultSelect.add(selectedItem);
//    }
//    function dataSelectDelete() {
//
//
//
//        var form2 = document.forms["resultForm"];
//        var select = form2.elements.result;
//        for (var i = 0; i < select.options.length; i++) {
//            var option = select.options[i];
//            if(option.selected) {
//                alert( option.value );
//            }
//        }
//
//
//    }
</script>

</body>
</html>
