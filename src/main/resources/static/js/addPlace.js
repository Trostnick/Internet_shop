$(document).ready(function () {
    $("#newPlace").submit(function (event) {
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
    params.name = $("#name").val();
    params.address = $("#address").val() || "";
    params.info = $("#info").val() || "";
    params.removed = false;

    params = JSON.stringify(params);

    $.ajax({
        url: "/api/place",
        type: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        data: params,
        dataType: 'json',
        success: function () {
            result_div.attr("class", "result alert alert-success").html('Успешно добавлено');
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

            result_div.attr("class", "result alert alert-danger").html("Данные введены неверно");
        }
    })
}