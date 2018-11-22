<!DOCTYPE html>
<html lang="eng">
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script type="text/javascript" src="/webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <br>
    <h1>Регистрация</h1>
    <div id="result">
    </div>
    <br>
    <form id="newUser">
        <div id="nameError" class="errorDiv"></div>
        <div class="form-group">
            <label for="name">Имя</label>
            <input type="text" class="form-control" id="name" required>
        </div>
        <div id="loginError" class="errorDiv"></div>
        <div class="form-group">
            <label for="login">Логин(используется для входа)</label>
            <input type="text" class="form-control" id="login">
        </div>
        <div id="passwordError" class="errorDiv"></div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password" class="form-control" id="password" required minlength="6">
        </div>
        <div id="passwordConfirmError" class="errorDiv"></div>
        <div class="form-group">
            <label for="passwordConfirm">Повторите пароль</label>
            <input type="password" class="form-control" id="passwordConfirm" required>
        </div>
        <button type="submit" class="btn btn-primary"> Зарегистрироваться</button>
    </form>

    <br>

    <form action="/home">
        <button type="submit" class="btn btn-info"> На главную </button>
    </form>
    <br>
</div>

<script type="text/javascript" src="/js/addClient.js"></script>

</body>
</html>