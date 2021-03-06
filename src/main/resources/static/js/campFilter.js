$(document).ready(function () {

    fireAjaxSubmit();
    $("#filter").submit(function (event) {
        event.preventDefault();
        fireAjaxSubmit();
    });



});

function fireAjaxSubmit() {
    var resultDiv = $("#result");
    var params = {};
    params.age = $("#campAge").val() || "";
    params.name = $("#campName").val() || "";
    params.dateStart = $("#campDateStart").val() || "";
    params.dateFinish = $("#campDateFinish").val() || "";
    params.place = $("#campPlace").val() || "";
    params.type = $("#campType").val() || "";
    params.priceMin = $("#campPriceMin").val() || "";
    params.priceMax = $("#campPriceMax").val() || "";
    params.page = 1;

    $.ajax({
        url: "/filter/camp",
        data: params,
        timeout: 600000,
        success: function (data) {
            resultDiv.html(data);
        },
        error: function (e) {
            resultDiv.attr("class", "result alert alert-danger");
            resultDiv.html(e.responseText)

        }
    })
}