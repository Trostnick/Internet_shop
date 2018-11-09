<html>
<head>
    <title>
        Корзина
    </title>
    <script src="/webjars/jquery/2.2.4/jquery.min.js"></script>
</head>
<body>
<form action="/home">
    <input type="submit" value="На главную">
</form>
<#if orderCampList??>
    <p> Номер заказа - ${orderCampList[0].order.id}
    <table border="1">
        <tr>
            <td>Наименование лагеря
            <td>Количество
            <td>Цена за 1 единицу в рублях
            <td>Удалить
        </tr>
        <#list orderCampList as orderCamp>
        <tr id="orderCamp-${orderCamp.id}">
            <td>${orderCamp.camp.name}
            <td class="count">${orderCamp.count}
            <td class="price">${orderCamp.camp.price}
            <td>

                <form onclick="return (confirm('Подтердите удаление?'))" class = "removeOrderCamp"
                data-id=${orderCamp.camp.id} <#--action="/"-->>
                <input type="submit" value="Удалить">

                </form>
            </td>
        </tr>
        </#list>
    </table>
<form onclick="return (confirm('Вы точно хотите очистить корзину?'))" action="/basket/remove">
    <input type="submit" value="Очистить корзину">
</form>
<p> Суммарная цена заказа -
<p id="orderPrice">${orderPrice}</p>
<form>
    <input type="submit" value="Подтвердить">
</form>
<#else>
<p>У вас нет заказов в корзине
</#if>
    <script src="/js/removeOrderCamp.js"></script>
</body>
</html>