<!DOCTYPE html>
<html lang="eng">
<head>
    <title>Авторизация</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/popper.js/1.14.3/popper.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>

<body>
<br>
<div class="container">
<#if error??>
<div><p class="text-danger"> Неверный логин или пароль</div>
</#if>

<#if logout??>
<div> <p class="text-warning">Вы вышли из своего профиля</div>
</#if>
    <br>
    <form action="/login" method="post">
        <div class="form-group">
            <label for="username"> Login : </label>
            <input type="text" class="form-control" name="username" id="username" required/>
        </div>
        <div class="form-group">
            <label for="password"> Password: </label>
            <input type="password" class="form-control" name="password" id="password" required/>
        </div>
        <button class="btn btn-primary">Войти</button>
    </form>
    <br>

    <form action="/home">
        <button type="submit" class="btn btn-info"> На главную</button>
    </form>
</div>
</body>
</html>