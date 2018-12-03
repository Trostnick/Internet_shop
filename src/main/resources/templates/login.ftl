<!DOCTYPE html>
<html lang="eng">
<head>
    <title>Авторизация</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>

<body>
<br>
<div class="container">
<#if error??>
<div><p class="alert alert-danger"> Неверный логин или пароль</div>
</#if>

<#if logout??>
<div> <p class="alert alert-warning">Вы вышли из своего профиля</div>
</#if>
    <h1>Вход</h1>
    <br>
    <form action="${springMacroRequestContext.contextPath}/login" method="post">
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

    <form action="${springMacroRequestContext.contextPath}/home">
        <button type="submit" class="btn btn-info"> На главную</button>
    </form>
    <br>
</div>
</body>
</html>