<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Internet Shop</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-sm bg-light justify-content-end">
        <#if user??>
            <ul class="navbar-nav">

                <li class="nav-item">
                    <p class="navbar-text">Добро пожаловать, <strong>${user.name}</strong></p>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${springMacroRequestContext.contextPath}/orders">Заказы</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${springMacroRequestContext.contextPath}/cart">Корзина</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${springMacroRequestContext.contextPath}/logout">Выйти</a>
                </li>
            </ul>


        <#else>
            <ul class="navbar-nav ">

                <li class="nav-item">
                    <a class="nav-link" href="${springMacroRequestContext.contextPath}/login"> Авторизироваться </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${springMacroRequestContext.contextPath}/registration">
                        Зарегистрироваться </a>
                </li>
            </ul>

        </#if>
    </nav>
    <h1>Интернет магазин </h1>
    <br>
    <div>
        <form id="filter">
            <div><label> Название <input type="text" id="campName"> </label></div>
            <div><label> Возраст <input type="number" id="campAge" min="0"> </label></div>
            <div><label> Дата от <input type="date" id="campDateStart"> </label></div>
            <div><label> Дата до <input type="date" id="campDateFinish"> </label></div>
            <div><label> Цена от <input type="number" id="campPriceMin" min="0"> рублей</label></div>
            <div><label> Цена до <input type="number" id="campPriceMax" min="0"> рублей</label></div>
            <button type="submit" class="btn btn-info">Поиск</button>
        </form>
    </div>
    <br>

    <div id="result"></div>

    <#if user?? && (user.status.id>1)>
        <div>
            <form action="/camp/add">
                <input type="submit" class="btn btn-primary" value="Добавить лагерь">
            </form>
        </div>
    </#if>
    <br>
</div>
<script type="text/javascript" src="/js/campFilter.js"></script>

</body>
</html>