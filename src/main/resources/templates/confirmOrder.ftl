<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Подтверждение заказа</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script type="text/javascript" src="/webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>Подтверждение</h1>
    <h6> Номер заказа - ${orderCampList[0].order.id}</h6>
    <br>
    <table border="1">
        <tr>
            <td>Наименование лагеря
            <td>Количество
            <td>Цена за 1 единицу в рублях
        </tr>
            <#list orderCampList as orderCamp>
            <tr>
                <td>${orderCamp.camp.name}
                <td>${orderCamp.count}
                <td>${orderCamp.camp.price}
            </tr>
            </#list>
    </table>
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
    <form action="/cart/confirm" method="post">
        <div class="form-group">
            <label for="address">Адрес доставки</label>
            <input class="form-control" type="text" id="address" required>
        </div>
        <div class="form-group">
            <label for="phoneNumber">Номер телефона для связи(формат ввода - ххх-ххх-хххх)</label>
            <input class="form-control" type="tel" id="phoneNumber"
                    required pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}">
        </div>
        <button type="submit" class="btn btn-primary">Подтвердить</button>
    </form>
    <br>
    <form action="/home">
        <button type="submit" class="btn btn-info"> На главную</button>
    </form>
</div>
</body>
</html>