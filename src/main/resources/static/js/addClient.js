$(document).ready(function () {
    $("#newUser").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    })

});

function fire_ajax_submit() {
    var result_div = $("#result");
    var params = {};
    params.password = $("#password").val();
    if (!(params.password === $("#passwordConfirm").val())) {
        result_div.attr("class", "alert alert-danger");
        result_div.html('Значение пароля не совпадает с его подтверждением');
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
                result_div.attr("class", "alert alert-success");
                result_div.html(res);
            },
            error: function (e) {
                result_div.attr("class", "alert alert-danger");
                result_div.html(e.responseText);
            }

        })
    }


}