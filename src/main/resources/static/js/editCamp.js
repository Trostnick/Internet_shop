$(document).ready(function () {
    $("#editCamp").submit(function (event) {
        event.preventDefault();
        var campId = ($(this).data("id"));
        fireAjaxSubmit(campId)
    })
});

function fireAjaxSubmit(campId) {
    var resultDiv = $(".result");
    var errorDiv = $(".errorDiv");
    errorDiv.attr("class", "errorDiv");
    errorDiv.html('');
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
        if (!(icon[0].length === 0)) {
            formData.append("icon", icon[0]);
        }
    }
    /*var photoArray = $("#photo").prop('files');
    if (!(photoArray.length === 0)) {
        for (i in photoArray) {
            if (!(photoArray[i] === 0)) {
                formData.append("photo", photoArray[i]);
            }
        }
    }*/

    $.ajax({
        type: 'PUT',
        url: "/api/camp/" + campId,
        enctype: "mulripart/form-data",
        data: formData,
        processData: false,
        contentType: false,
        success: function () {
            resultDiv.attr("class", "result alert alert-success").html('Успешно изменено');
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
                resultDiv.attr("class", "result alert alert-danger").html("Данные введены неверно");

            }
        }


    })

}

