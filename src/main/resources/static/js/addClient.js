$(document).ready(function () {
    $("#newUser").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    })

});

function fire_ajax_submit() {
    var params = {};
    params.password = $("#password").val();
    if (!(params.password === $("#passwordConfirm").val())) {
        $("#result").html('<p>значение пароля не совпадает с его подтверждением');
    }
    else {
        params.name = $("#username").val();
        params.login = $("#login").val();
        $.ajax({
            type: "POST",
            url: "/api/user",
            contentType: 'application/json',
            data: JSON.stringify(params),
            success: function (res) {
                $("#result").html('<p>' + res);
            },
            error: function (e) {
                $("#result").html('<p>' + e.responseText);
            }

        })
    }


}