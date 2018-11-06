<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Internet Shop</title>
</head>
<body>

<#if notAuthoraized??>
    <a href="/login">Авторизироваться</a>
<#else>
    <h1>Добро пожаловать, ${username}</h1>
    <form action="/logout">
        <button>
            Выйти
        </button>
    </form>
</#if>

<form id="filter">
    <div><label> Название <input type="text" id="campName"> </label></div>
    <div><label> Возраст от <input type="number" id="campAgeMin"> </label></div>
    <div><label> Возраст до <input type="number" id="campAgeMax"> </label></div>
    <div><label> Дата от <input type="date" id="campDateStart"> </label></div>
    <div><label> Дата до <input type="date" id="campDateFinish"> </label></div>
    <div><label> Место <input type="text" id="campPlace"> </label></div>
    <div><label> Тип лагеря <input type="text" id="campType"> </label></div>
    <button type="submit">Поиск</button>
</form>



<div id="feedback"></div>

<div>
    <form action="/camp">
        <input type="submit" value="Добавить лагерь">
</div>

</body>

<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>

<script type="text/javascript" src="js/campFilter.js"></script>

</html>