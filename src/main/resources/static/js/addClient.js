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
        $("#error").html('<p class="text-danger">Значение пароля не совпадает с его подтверждением');
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
                $("#success").html('<p class="text-success">' + res);
            },
            error: function (e) {
                $("#error").html('<p class="text-danger">' + e.responseText);
            }

        })
    }


}