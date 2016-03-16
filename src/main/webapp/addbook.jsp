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
        <p><h2 class="centred">${bread} > ${pageName}</h2></p>
    </div>
</div>

<div class="parent big_topspace">
    <p>
    <div class="block">
        <form class="centred" method="POST">
            <p><input type="text" value="" maxlength="64" placeholder="Название книги" name="title" required pattern=".*" title="Введите название книги"></p>
            <p><input type="text" value="" placeholder="Год публикации" name="pubYear" pattern="-?\d{4}" title="Введите год в формате ГГГГ" required></p>
            <%--<p><input placeholder="Жанр id" name="genereId" required></p>--%>

            <p><select class="listMulticatch" size="1" name="genereId">
                <option disabled>Жанр</option>
                <c:forEach var="genre" items="${sourceListGenre}">
                    <p>
                        <option value="${genre.id}">${genre.title}</option>
                    </p>
                </c:forEach>
                
            </select></p>

            <p><button formaction="addbook">Добавить</button></p>
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




<script>
    function dataSelectAdd() {
        var form1 = document.forms["sourceForm"];
        var selectedItem = form1.elements.source;

        for (var i = 0; i < selectedItem.options.length; i++) {
            var option = selectedItem.options[i];
            if(option.selected) {
                alert( option.value );
            }
        }

//        var option = new Option("Текст", "value", true, true);

        var form2 = document.forms["resultForm"];
        //var option2 = new Option("Текст ++++++++++++++++", "999", true, true);
        var resultSelect = form2.elements.result;
        resultSelect.add(selectedItem);
        //resultSelect.appendChild(option2);

//        tmp.add(selectedItem)
//        selection.add(selectedItem);










        // one select only
//        var target = form.source.selectedIndex
//        var forAdd;
//        if(target) alert("Выбран автор c id: " + form.source.options[target].value)
//        if(target) forAdd = form.source.options[target].value;

        // option = new Option(text, value, defaultSelected, selected);
        // var option = new Option("Текст", "value", true, true);

    }
    function dataSelectDelete() {



        var form2 = document.forms["resultForm"];
        var select = form2.elements.result;
        for (var i = 0; i < select.options.length; i++) {
            var option = select.options[i];
            if(option.selected) {
                alert( option.value );
            }
        }


    }
</script>

</body>
</html>
