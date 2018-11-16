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
    <br>
    <div id="result">
    </div>
    <form id="addToBasket">
        <ul>
            <li>Наименовние лагеря - ${camp.name}
            <li>Цена за 1 путевку - ${camp.price}
            <li><label>Количество - <input type="number" id="count" value="1" min="1" required></label>
        </ul>
        <button type="submit" class="btn btn-primary">Добвить</button>
    </form>
    <br>
    <form action="/home">
        <button type="submit" class="btn btn-info">На главную</button>
    </form>
    <br>
</div>
<script>
    var campId =${camp.id}
</script>

<script src="/js/addToBasket.js"></script>

</body>
</html>