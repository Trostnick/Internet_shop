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
        <td class="text-center"> <#if camp.icon??><img src="${springMacroRequestContext.contextPath}/api/camp/icon/small/${camp.id}" width="50" height="50" alt="icon"><#else > icon</#if>
        <td> ${camp.name}
        <td> <#if camp.place??>${camp.place.name}</#if>
        <td> <#if camp.type??>${camp.type.name}</#if>
        <td> ${camp.price}
        <td> от ${camp.ageMin} до ${camp.ageMax} лет
        <td> с ${camp.dateStart} до ${camp.dateFinish}
        <td>
        <form action="${springMacroRequestContext.contextPath}/camp/${camp.id}">
            <button type="submit" class="btn btn-info">Перейти</button>
        </form>
        <td>
        <form action="${springMacroRequestContext.contextPath}/cart/${camp.id}">
            <button type="submit" class="btn btn-success">Добавить</button>
        </form>
    </tr>

</#list>
<#else ><h1>На данный момент нет доступных лагерей, обратитесь позднее</h1>
</#if>
    </table>
</div>