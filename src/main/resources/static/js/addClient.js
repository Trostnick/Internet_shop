$(document).ready(function () {
    $("#newUser").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    })

});

function fire_ajax_submit() {
    var result_div = $("#result");
    var error_div = $(".errorDiv");
    error_div.attr("class", "errorDiv");
    error_div.html('');
    var params = {};
    params.password = $("#password").val();
    params.name = $("#name").val();
    params.login = $("#login").val();
    params.passwordConfirm = $("#passwordConfirm").val();

    $.ajax({
        type: "POST",
        url: "/api/user",
        contentType: 'application/json',
        data: JSON.stringify(params),
        success: function () {
            result_div.attr("class", "alert alert-success").html("Пользователь создан!");
        },
        error: function (e) {
            var jsonMessage = JSON.parse(e.responseText);
            for (fieldName in jsonMessage) {
                var messages = jsonMessage[fieldName];
                var resultMessage = "";
                for (i in messages) {
                    resultMessage += '<p>' + messages[i] + '</p>';
                }
                $("#" + fieldName + "Error").attr("class", "errorDiv alert alert-danger").html(resultMessage);
            }
            result_div.attr("class", "alert alert-danger").html("Данные введены неверно");
        }

    })


}