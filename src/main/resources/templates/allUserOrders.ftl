<body>
<form action="/home">
    <input type="submit" value="На главную">
</form>

<table border="2">
    <#if orders??>
        <tr>
            <td> Номер заказа
            <td> Статус
            <td> Подробнее
        </tr>
        <#list orders as order>
        <tr>
            <td> ${order.id}
            <td> ${order.status.name}
            <td> <a href="/basket/${order.id}">Перейти<a>
        </tr>
        </#list>
    <#else>
    <p>У вас нет ни одного заказа
    </#if>
</table>
</body>