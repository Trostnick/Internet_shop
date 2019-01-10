<div>
<table border="1">

<#if campFullCount == 0>
    <h1>На данный момент нет доступных лагерей, обратитесь позднее</h1>
<#elseif campCount == 0>
    <h1>На данный момент нет лагерей, подходящих условиям фильтрации</h1>
<#elseif campList??>
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
<#list campList as camp>
    <tr>
        <td class="text-center"> <#if camp.icon??><img src="/api/camp/icon/small/${camp.id}"
                                                       width="50" height="50" alt="icon"></#if>
        <td> ${camp.name}
        <td> <#if camp.place??>${camp.place.name}</#if>
        <td> <#if camp.type??>${camp.type.name}</#if>
        <td> ${camp.price}
        <td> от ${camp.ageMin} до ${camp.ageMax}
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
</table>
<nav>
    <ul class="pagination">
        <#if prevPage??>
        <li class="page-item"><a class="page-link pageButton" data-page="${prevPage}">Previous</a></li>
        <#else>
        <li class="page-item "><a class="page-link" data-page="${page}">Previous</a></li>
        </#if>
            <#if firstPage??>
                <li class="page-item"><a class="page-link pageButton" data-page="${firstPage}">${firstPage}</a></li>
            </#if>
            <#if notInStart??>
                <li class="page-item"><a class="page-link " >...</a></li>
            </#if>
        <#if prevPage??>
            <li class="page-item"><a class="page-link pageButton" data-page="${prevPage}">${prevPage}</a></li>
        </#if>

        <li class="page-item active"><a class="page-link pageButton" data-page="${page}">${page}</a></li>

        <#if nextPage??>
            <li class="page-item"><a class="page-link pageButton" data-page="${nextPage}">${nextPage}</a></li>
        </#if>
            <#if notInFinish??>
                <li class="page-item disabled"><a class="page-link" >...</a></li>
            </#if>
            <#if lastPage??>
                <li class="page-item"><a class="page-link pageButton" data-page="${lastPage}">${lastPage}</a></li>
            </#if>
        <#if nextPage??>
            <li class="page-item"><a class="page-link pageButton" data-page="${nextPage}">Next</a></li>
        <#else>
        <li class="page-item disable"><a class="page-link" data-page="${page}">Next</a></li>
        </#if>
        <li class="page-item"> <p> Всего обнаружено лагерей - ${campCount} </p></li>
    </ul>
</nav>
</#if>
<script type="text/javascript" src="/js/pagination.js"></script>

</div>