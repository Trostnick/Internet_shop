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
    <h1>Корзина</h1>
    <br>
    <#if orderCampList??>
        <h6> Номер заказа - ${orderCampList[0].order.id}</h6>
        <br>
        <table border="1">
            <tr>
                <td>Наименование лагеря
                <td>Количество
                <td>Цена за 1 единицу в рублях
                <td>Удалить
            </tr>
            <#list orderCampList as orderCamp>
            <tr id="orderCamp-${orderCamp.id}" class="orderCamp">
                <td>${orderCamp.camp.name}
                <td> <input class="count" type="number" min="1" required data-count=${orderCamp.count} data-id=${orderCamp.id}
                     value=${orderCamp.count} >
                <td class="price">${orderCamp.camp.price}
                <td>
                    <form onclick="return (confirm('Подтердите удаление?'))" class="removeOrderCamp"
                          data-id=${orderCamp.id} >
                        <button type="submit" class="btn btn-danger"  >Удалить</button>
                    </form>
                </td>
            </tr>
            </#list>
        </table>
    <form onclick="return (confirm('Вы точно хотите очистить корзину?'))" action="/cart/remove">
        <button class="btn btn-danger" id="removeCart">Очистить корзину</button>
    </form>
    <br>
    <div class="row">
    <div class="col-3">
    <p> Суммарная цена заказа -</p>
    </div>
    <div class="col-1">
        <p id="orderPrice">${orderPrice}</p>
    </div>
    </div>
        <br>
    <form action="/cart/confirm">
        <button type="submit" class="btn btn-primary">Подтвердить</button>
    </form>
    <#else>
    <p>У вас нет заказов в корзине
    </#if>
    <br>
    <form action="/home">
        <button type="submit" class="btn btn-info" > На главную</button>
    </form>
    <br>
</div>


<script src="/js/cart.js"></script>
</body>
</html>