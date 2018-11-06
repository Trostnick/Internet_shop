<!DOCTYPE html>
<html>
<body>
<div>
    <form action="/home">
        <button>На главную</button>
    </form>
</div>

<div id="result"></div>

<p>Если место проведения вашего лагеря отсутствует, не забудьте сначала добавить его</p>
<form action="/place">
    <input type="submit" value="Добавить">
</form>

<form  id="newCamp">
    <div><label>Название :
        <input type="text" id="name"  minlength="2" required>
    </label></div>
    <div><label> Возраст от
        <input type="number" id="ageMin" min="0" max="120" required size="3">
    </label></div>
    <div><label> Возраст до
        <input type="number" id="ageMax" min="0" max="120" required size="3">
    </label></div>
    <div><label> Дата от
        <input type="date" id="dateStart" required>
    </label></div>
    <div><label> Дата до
        <input type="date" id="dateFinish" required>
    </label></div>
    <div><label> Количество детей
        <input type="number" id="childrenCount" min="0" max="1000" required size="4">
    </label></div>
    <div><label> Место
        <select id="place">
                <#list places as place>
                    <option value="${place.id}">${place.name}</option>
                </#list>
        </select>
    </label></div>
    <div><label> Место
        <select id="type">
                <#list types as type>
                    <option value="${type.id}">${type.name}</option>
                </#list>
        </select>
    </label></div>
    <div><label> Информация
        <textarea rows="5" id="info"></textarea>
    </label>
    </div>
    <div><label> Иконка
        <input type="file" id="icon">
    </label></div>
    <input type="submit" value="Отправить">
</form>

</body>

<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>

<script type="text/javascript" src="js/addCamp.js"></script>
</html>

