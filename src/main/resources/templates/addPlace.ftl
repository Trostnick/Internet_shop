<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Место проведения</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script type="text/javascript" src="/webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>Добавление нового места</h1>
    <br>


    <div id="result"></div>

    <div>
        <form id="newPlace">
            <div id="nameError" class="errorDiv"></div>
            <div class="form-group">
                <label for="name"> Наименование</label>
                <input type="text" class="form-control" id="name" min="" required>
            </div>
            <div class="form-group">
                <label for="address"> Адрес</label>
                <input type="text" class="form-control" id="address">
            </div>
            <div class="form-group">
                <label for="info"> Дополнительная информация</label>
                <textarea id="info" class="form-control" rows="5"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Добавить</button>
        </form>
    </div>

    <br>
    <div class="row">
        <div class="col-2">
            <form action="/camp">
                <button type="submit" class="btn btn-warning">К добавлению лагеря</button>
            </form>
        </div>
        <div class="col-2">
            <form action="/home">
                <button type="submit" class="btn btn-info">На главную</button>
            </form>
        </div>
        <br>
    </div>
</div>
<script type="text/javascript" src="js/addPlace.js"></script>
</body>

