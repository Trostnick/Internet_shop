<body>
<div>
    <form action="/home">
        <button>На главную</button>
    </form>
</div>
<div>
    <form action="/camp">
        <button>Назад</button>
    </form>
</div>

<div id="result"></div>

<div>
    <form id="newPlace">
        <div><label> Наименование
            <input type="text" id="name" min="" required>
        </label></div>
        <div><label> Адрес
            <input type="text" id="address">
        </label></div>
        <div><label> Дополнительная информация
            <textarea id="info" rows="5"></textarea>
        </label></div>
        <input type="submit" value="Добавить">
    </form>
</div>

</body>

<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>

<script type="text/javascript" src="js/addPlace.js"></script>