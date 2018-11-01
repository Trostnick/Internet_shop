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

<h1> Все доступные лагеря</h1>
<table border="1">
    <tr>
        <td>Иконка
        <td>Название
        <td>Место проведения
        <td>Тип лагеря
        <td>Возраст
        <td>Дата проведения
        <td>Подробнее
    </tr>
    <#list camps as camp>
    <tr>
        <td> <#if camp.icon??><img src="/api/camp/icon/${camp.id}" alt="icon" height="50" width="50"><#else > icon</#if>
        <td> ${camp.name}
        <td> <#if camp.place??>${camp.place.name}</#if>
        <td> <#if camp.type??>${camp.type.name}</#if>
        <td> от ${camp.ageMin} до ${camp.ageMax} лет
        <td> с ${camp.dateStart} до ${camp.dateFinish}
        <td><a href="/camp/${camp.id}">Перейти
    </tr>
    <#else ><h1>На данный момент нет доступных лагерей, обратитесь позднее</h1>
    </#list>

</table>

</html>