<!DOCTYPE html>
<html>
<head>
    <title>Авторизация</title>
</head>

<body>
<#if error??>
<div> Неверный логин или пароль</div>
</#if>

<#if logout??>
<div> Вы вышли из своего профиля</div>
</#if>

<form action="/home">
    <input type="submit" value="На главную">
</form>

    <form action="/login" method="post">
        <div><label> Login : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><button>Войти</button></div>
    </form>
</body>
</html>