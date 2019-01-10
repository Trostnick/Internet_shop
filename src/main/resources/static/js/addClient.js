$(document).ready(function () {
    $("#newUser").submit(function (event) {
        event.preventDefault();
        fireAjaxSubmit();
    })

});

function fireAjaxSubmit() {
    var resultDiv = $("#result");
    var errorDiv = $(".errorDiv");
    errorDiv.attr("class", "errorDiv");
    errorDiv.html('');
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
            resultDiv.attr("class", "alert alert-success").html("Пользователь создан!");
        },
        error: function (e) {
            if (e.status !== 400) {
                resultDiv.attr("class", "result alert alert-danger").html(e.statusText);
            }
            else {
                var jsonMessage = JSON.parse(e.responseText);
                for (fieldName in jsonMessage) {
                    var messages = jsonMessage[fieldName];
                    var resultMessage = "";
                    for (i in messages) {
                        resultMessage += '<p>' + messages[i] + '</p>';
                    }
                    $("#" + fieldName + "Error").attr("class", "errorDiv alert alert-danger").html(resultMessage);
                }
                resultDiv.attr("class", "alert alert-danger").html("Данные введены неверно");
            }
        }

    })


}