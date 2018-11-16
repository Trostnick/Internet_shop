$(document).ready(function () {
    $("#newPlace").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    })
});

function fire_ajax_submit() {
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
            $("#result").html('<p class="text-success">Успешно добавлено')
        },
        error:function (e) {
            $("#result").html('<p class="text-danger">' + e.responseText)
        }
    })
}