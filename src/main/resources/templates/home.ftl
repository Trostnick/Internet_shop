<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Internet Shop</title>
    <script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
</head>
<body>

<#if user??>
<h1>Добро пожаловать, ${user.name}</h1>
    <div>
        <form action="/orders">
            <input type="submit" value="Просмотр заказов">
        </form>
        <form action="/basket">
            <input type="submit" value="Просмотр корзины">
        </form>
        <form action="/logout">
            <button>
                Выйти
            </button>
        </form>
    </div>
<#else>
    <form action="/login">
        <input type="submit" value="Авторизироваться">
    </form>
    <div>
        <p> У вас еще нет профиля?
        <form action="/registration">
            <input type="submit" value="Зарегистрироваться">
        </form>
    </div>
</#if>

<form id="filter">
    <div><label> Название <input type="text" id="campName"> </label></div>
    <div><label> Возраст <input type="number" id="campAge" min="0"> </label></div>
    <div><label> Дата от <input type="date" id="campDateStart"> </label></div>
    <div><label> Дата до <input type="date" id="campDateFinish"> </label></div>
<#--<div><label> Место <input type="text" id="campPlace"> </label></div>-->
<#--<div><label> Тип лагеря <input type="text" id="campType"> </label></div>-->
    <div><label> Цена от <input type="number" id="campPriceMin" min="0"> рублей</label></div>
    <div><label> Цена до <input type="number" id="campPriceMax" min="0"> рублей</label></div>
    <button type="submit">Поиск</button>
</form>


<div id="feedback"></div>

<div>
    <form action="/camp">
        <input type="submit" value="Добавить лагерь">
</div>

<script type="text/javascript" src="js/campFilter.js"></script>

</body>

</html>