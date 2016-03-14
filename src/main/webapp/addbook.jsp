<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<h1>Добавление книги</h1>

<form method="POST">

    <p><input type="text" value="" maxlength="64" placeholder="Название книги" name="title" required pattern=".*" title="Введите название книги"></p>
    <p><input type="text" value="" placeholder="Год публикации" name="pubYear" pattern="-?\d{4}" title="Введите год в формате ГГГГ"></p>
    <p><input placeholder="Жанр id" name="genereId"></p>
    <p><button formaction="addbook">Добавить</button></p>
</form>


<form action="handler.php">
    <p><select size="5" name="hero">
        <option>Выберите героя</option> // цикл
        <option value="Чебурашка">Чебурашка</option>
        <option value="Крокодил Гена">Крокодил Гена</option>
        <option value="Шапокляк">Шапокляк</option>
        <option value="Крыса Лариса">Крыса Лариса</option>
    </select>
        <input type="button" value="<<" onClick="dataSelectAdd(this.form)"></p>
        <input type="button" value=">>" onClick="dataSelectDelete(this.form)"></p>
</form>

<script>
    function dataSelectAdd(form) {
        var target = form.hero.selectedIndex
        if(target) alert("Выбран герой: " + form.hero.options[target].value)
    }
//    document.getElementById('id')
</script>

</body>
</html>
