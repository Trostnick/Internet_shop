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
    <h1> Редактирование лагеря "${camp.name}"</h1>
    <br>


    <br>
    <form id="editCamp" data-id="${camp.id}">
        <div id="nameError" class="errorDiv"></div>
        <div class="form-group">
            <label for="name">Название </label>
            <input type="text" class="form-control" id="name" value="${camp.name}" required>
        </div>
        <div id="ageMinError" class="errorDiv"></div>
        <div class="form-group">
            <label for="ageMin"> Возраст от</label>
            <input type="number" class="form-control" id="ageMin" min="0" max="120" size="3"
                   value="${camp.ageMin?c}" required>
        </div>
        <div id="ageMaxError" class="errorDiv"></div>
        <div class="form-group">
            <label for="ageMax"> Возраст до</label>
            <input type="number" class="form-control" id="ageMax" min="0" max="120" size="3"
                   value="${camp.ageMax?c}" required>
        </div>
        <div id="dateStartError" class="errorDiv"></div>
        <div class="form-group">
            <label for="dateStart"> Дата от</label>
            <input type="date" class="form-control" id="dateStart" value="${camp.dateStart}" required>
        </div>
        <div id="dateFinishError" class="errorDiv"></div>
        <div class="form-group">
            <label for="dateFinish"> Дата до</label>
            <input type="date" class="form-control" id="dateFinish" value="${camp.dateFinish}" required>
        </div>
        <div id="childrenCountError" class="errorDiv"></div>
        <div class="form-group">
            <label for="childrenCount"> Количество детей</label>
            <input type="number" class="form-control" id="childrenCount" min="0" max="1000" size="4"
                   value="${camp.childrenCount?c}" required>
        </div>
        <div id="priceError" class="errorDiv"></div>
        <div class="form-group">
            <label for="price"> Цена в рублях</label>
            <input type="number" class="form-control" id="price" min="0" size="10"
                   required value="${camp.price?c}"  >
        </div>
        <div class="form-group">
            <label for="place"> Место проведения</label>
            <select class="form-control" id="place">
                <#if placeList??>
                    <#list placeList as place>
                        <option <#if camp.place.id==place.id > selected </#if> value="${place.id}">
                            ${place.name}
                        </option>
                    </#list>
                </#if>
            </select>
        </div>
        <div class="form-group">
            <label for="type"> Тип лагеря</label>
            <select class="form-control" id="type">
                <#if typeList??>
                    <#list typeList as type>
                        <option <#if camp.type.id==type.id > selected </#if> value="${type.id}">
                            ${type.name}
                        </option>
                    </#list>
                </#if>
            </select>
        </div>
        <div class="form-group">
            <label for="info"> Дополнительная информация </label>
            <textarea rows="5" class="form-control" id="info">${camp.info}</textarea>
        </div>
        <div class="form-group">
            <label for="icon"> Новая иконка </label>
            <input type="file" id="icon">
        </div>
        <#--<div id="photoError" class="errorDiv"></div>
        <div class="form-group">
            <label for="photo"> Фотографии</label>
            <input type="file" id="photo" multiple>
        </div>-->
        <button type="submit" class="btn btn-primary">Изменить</button>
    </form>
    <br>
    <div class="result"></div>
    <br>
    <form action="/home">
        <button type="submit" class="btn btn-info">На главную</button>
    </form>
    <br>
</div>
<script type="text/javascript" src="/js/editCamp.js"></script>
</body>


</html>