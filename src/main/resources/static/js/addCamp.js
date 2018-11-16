$(document).ready(function () {
    $("#newCamp").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit()
    })
});

function fire_ajax_submit() {

    var formData = new FormData();
    formData.append("price", $("#price").val());
    formData.append("name", $("#name").val());
    formData.append("ageMin", $("#ageMin").val());
    formData.append("ageMax", $("#ageMax").val());
    formData.append("dateStart", $("#dateStart").val());
    formData.append("dateFinish", $("#dateFinish").val());
    formData.append("childrenCount", $("#childrenCount").val());
    formData.append("info", $("#info").val() || "");
    formData.append("placeId", $("#place").val());
    formData.append("typeId", $("#type").val());
    formData.append("icon", $("#icon").prop('files')[0]);

    $.ajax({
        type: 'POST',
        url: "/api/camp",
        /*contentType: 'application/json',
        data: params,
        dataType: 'json',*/
        enctype: "mulripart/form-data",
        data: formData,
        processData: false,
        contentType: false,
        success: function () {
            $("#result").html('<p class="text-success">Успешно добавлено')
        },
        error: function (e) {
            $("#result").html('<p class="text-danger">' + e.responseText)
        }


    })

}

