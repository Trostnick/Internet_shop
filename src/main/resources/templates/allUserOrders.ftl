<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Корзина</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script type="text/javascript" src="/webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <h1>Ваши заказы</h1>
    <br>
    <table border="2">
    <#if orderList??>
        <tr>
            <td> Номер заказа
            <td> Статус
        </tr>
        <#list orderList as order>
        <tr>
            <td> ${order.id}
            <td> ${order.status.name}
        </tr>
        </#list>
    <#else>
    <p>У вас нет ни одного заказа
    </#if>
    </table>
    <br>
    <form action="/home">
        <button type="submit" class="btn btn-info">На главную</button>
    </form>
    <br>
</div>
</body>