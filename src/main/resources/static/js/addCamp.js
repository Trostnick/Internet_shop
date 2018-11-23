$(document).ready(function () {
    $("#newCamp").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit()
    })
});

function fire_ajax_submit() {
    var result_div = $(".result");
    var error_div = $(".errorDiv");
    error_div.attr("class", "errorDiv");
    error_div.html('');
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
    var icon = $("#icon").prop('files');
    if (!(icon.length === 0)) {
        formData.append("icon", icon[0]);
    }
    var photo_array = $("#photo").prop('files');
    if(!(photo_array.length === 0)){
        for (i in photo_array) {
            formData.append("photo", photo_array[i]);
        }
    }


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

