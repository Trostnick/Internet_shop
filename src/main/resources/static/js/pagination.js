$(document).ready(function () {

    $(".pageButton").click(function (event) {
        event.preventDefault();
        var page = $(this).data("page");
        pagination(page);
    })


});

function pagination(page) {
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


    $.ajax({
        url: "/filter/camp?page=" + page,
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