<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${camp.name}</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/popper.js/1.14.3/popper.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div>
        <form action="/home">
            <button>На главную</button>
        </form>
    </div>
    <h1> ${camp.name}</h1>
<#if camp.icon??>
    <img src="/api/camp/icon/${camp.id}" alt="icon" height="150" width="150">
<#else>
<div>Здесь будет картинка</div>
</#if>
    <ul>
    <li>Цена - ${camp.price}
            <#if camp.place??>
        <li>Место проведения                - ${camp.place.name}
            </#if>
            <#if camp.type??>
        <li>Форма проведения - ${camp.type.name}
            </#if>
        <li>Дата начала - ${camp.dateStart}
        <li>Дата окончания - ${camp.dateFinish}
        <li>Минимальный возраст ребенка - ${camp.ageMin}
        <li>Максимальный возраст ребенка - ${camp.ageMax}
    <li>Количество детей - ${camp.childrenCount}
            <#if camp.info??>
        <li> Дополнительная информация - ${camp.info}
            </#if>

    </ul>

    <#if camp.place??>
        <h2>О месте проведения</h2>
        <div>

        <p>Название - ${camp.place.name}
            <#if camp.place.address??>
            <p>Адрес                        - ${camp.place.address}
            </#if>
            <#if camp.place.info??>
            <p>Дополнительная информация - ${camp.place.info}
            </#if>

        </div>
    </#if>
    <form action="/basket/${camp.id}">
        <input type="submit" value="Добавить в корзину">
    </form>
</div>
</body>
</html>