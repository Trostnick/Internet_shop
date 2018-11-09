<html>

<head>
    <title>Корзина</title>
    <script type="text/javascript" src="/webjars/jquery/2.2.4/jquery.min.js"></script>
</head>
<body>

<form action="/home">
    <input type="submit" value="На главную">
</form>

<div id="result">
</div>
<form id="addToBasket">
    <ul>
        <li>Наименовние лагеря - ${camp.name}
        <li>Цена за 1 путевку - ${camp.price}
        <li><label>Количество - <input type="number" id="count" value="1" min="1" required></label>
    </ul>
    <input type="submit" value="Добавить в корзину">
</form>

<script>
    var campId=${camp.id}
</script>

<script src="/js/addToBasket.js"></script>

</body>
</html>