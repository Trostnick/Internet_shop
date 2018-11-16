<div>
<table border="1">
<#if filterCamp??>
    <tr>
        <td>Иконка
        <td>Название
        <td>Место проведения
        <td>Тип лагеря
        <td>Цена (в рублях)
        <td>Возраст
        <td>Дата проведения
        <td>Подробнее
        <td>Добавить в корзину
    </tr>
<#list filterCamp as camp>
    <tr>
        <td> <#if camp.icon??><img src="/api/camp/icon/${camp.id}" alt="icon" height="50" width="50"><#else > icon</#if>
        <td> ${camp.name}
        <td> <#if camp.place??>${camp.place.name}</#if>
        <td> <#if camp.type??>${camp.type.name}</#if>
        <td> ${camp.price}
        <td> от ${camp.ageMin} до ${camp.ageMax} лет
        <td> с ${camp.dateStart} до ${camp.dateFinish}
        <td>
        <form action="/camp/${camp.id}">
            <button type="submit" class="btn btn-info">Перейти</button>
        </form>
        <td>
        <form action="/basket/${camp.id}">
            <button type="submit" class="btn btn-success">Добавить</button>
        </form>
    </tr>

</#list>
<#else ><h1>На данный момент нет доступных лагерей, обратитесь позднее</h1>
</#if>
    </table>
</div>