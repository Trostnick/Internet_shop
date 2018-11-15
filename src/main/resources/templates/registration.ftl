<html>
<head>
    <title>Регистрация</title>
    <script type="text/javascript" src="/webjars/jquery/2.2.4/jquery.min.js"></script>
</head>
<body>
<form action="/home">
    <input type="submit" value="На главную">
</form>

<div id="result">
</div>

<form id="newUser">
    <div><label>Имя
        <input type="text" id="username" required>
    </label></div>
    <div><label>Логин(используется для входа)
        <input type="text" id="login">
    </label></div>
    <div><label>Пароль
        <input type="password" id="password" required minlength="6">
    </label></div>
    <div><label>Повторите пароль
        <input type="password" id="passwordConfirm" required>
    </label></div>
    <input type="submit" value="Зарегистрироваться">
</form>
<script type="text/javascript" src="/js/addClient.js"></script>
</body>
</html>