<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Лагерь</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script type="text/javascript" src="/webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1> Добавление лагеря</h1>
    <br>

    <p>Если место проведения вашего лагеря отсутствует, не забудьте сначала добавить его</p>
    <form action="/place">
        <button type="submit" class="btn btn-success"> Добавить место проведения</button>
    </form>
    <br>
    <form id="newCamp">
        <div id="nameError" class="errorDiv"></div>
        <div class="form-group">
            <label for="name">Название </label>
            <input type="text" class="form-control" id="name" required>
        </div>
        <div id="ageMinError" class="errorDiv"></div>
        <div class="form-group">
            <label for="ageMin"> Возраст от</label>
            <input type="number" class="form-control" id="ageMin" min="0" max="120" required size="3">
        </div>
        <div id="ageMaxError" class="errorDiv"></div>
        <div class="form-group">
            <label for="ageMax"> Возраст до</label>
            <input type="number" class="form-control" id="ageMax" min="0" max="120" required size="3">
        </div>
        <div id="dateStartError" class="errorDiv"></div>
        <div class="form-group">
            <label for="dateStart"> Дата от</label>
            <input type="date" class="form-control" id="dateStart" required>
        </div>
        <div id="dateFinishError" class="errorDiv"></div>
        <div class="form-group">
            <label for="dateFinish"> Дата до</label>
            <input type="date" class="form-control" id="dateFinish" required>
        </div>
        <div id="childrenCountError" class="errorDiv"></div>
        <div class="form-group">
            <label for="childrenCount"> Количество детей</label>
            <input type="number" class="form-control" id="childrenCount" min="0" max="1000" required size="4">
        </div>
        <div id="priceError" class="errorDiv"></div>
        <div class="form-group">
            <label for="price"> Цена в рублях</label>
            <input type="number" class="form-control" id="price" min="0" required size="10">
        </div>
        <div class="form-group">
            <label for="place"> Место проведения</label>
            <select class="form-control" id="place">
                <#if places??>
                    <#list places as place>
                        <option value="${place.id}">${place.name}</option>
                    </#list>
                </#if>
            </select>
        </div>
        <div class="form-group">
            <label for="type"> Тип лагеря</label>
            <select class="form-control" id="type">
                <#if types??>
                    <#list types as type>
                        <option value="${type.id}">${type.name}</option>
                    </#list>
                </#if>
            </select>
        </div>
        <div class="form-group">
            <label for="info"> Дополнительная информация </label>
            <textarea rows="5" class="form-control" id="info"></textarea>
        </div>
        <div class="form-group">
            <label for="icon"> Иконка</label>
            <input type="file" id="icon">
        </div>
        <button type="submit" class="btn btn-primary">Добавить</button>
    </form>
    <br>
    <div class="result"></div>
    <br>
    <form action="/home">
        <button type="submit" class="btn btn-info">На главную</button>
    </form>
    <br>
</div>
<script type="text/javascript" src="js/addCamp.js"></script>
</body>


</html>

