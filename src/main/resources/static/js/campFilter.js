$(document).ready(function () {

    fire_ajax_submit();
    $("#filter").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    })
});

function fire_ajax_submit() {

    var params = {};
    params.ageMin = $("#campAgeMin").val() || "";
    params.ageMax = $("#campAgeMax").val() || "";
    params.name = $("#campName").val() || "";
    params.dateStart = $("#campDateStart").val() || "";
    params.dateFinish = $("#campDateFinish").val() || "";
    params.place = $("#campPlace").val() || "";
    params.type = $("#campType").val() || "";



    $.ajax({
        url: "/filter/camp",
        data: params,
        timeout: 600000,
        success: function (data) {
            $("#feedback").html(data);
        }/*,
        error: function (e) {
            $("#feedback").html("<p>" + e.responseText)

        }*/
    })
}