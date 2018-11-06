$(document).ready(function () {
    $("#newCamp").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit()
    })
});

function fire_ajax_submit() {
    var params = {};
    var place = $("#place");
    var type = $("#type");

    var icon = $("#icon").prop('files')[0];
    var reader = new FileReader();
    params["icon"] = [];
    reader.onloadend = function () {
        var arrayBuffer = this.result,
            array = new Uint8Array(arrayBuffer);
        for (var i = 0; i < array.length; i++) {
            params["icon"].push(array[i]);
        }
    };

    reader.readAsArrayBuffer(icon);
    params["removed"] = false;
    params["name"] = $("#name").val();
    params["ageMin"] = $("#ageMin").val();
    params["ageMax"] = $("#ageMax").val();
    params["dateStart"] = $("#dateStart").val();
    params["dateFinish"] = $("#dateFinish").val();
    params["childrenCount"] = $("#childrenCount").val();
    params["info"] = $("#info").val() || "";

    params["place"] = {};
    params["place"]["id"] = place.val();
    params["type"] = {};
    params["type"]["id"] = type.val();
    params = JSON.stringify(params);

    $.ajax({
        type: 'POST',
        url: "/api/camp",
        contentType: 'application/json',
        data: params,
        dataType: 'json',
        success: function () {
            $("#result").html('<p>Успешно добавлено')
        },
        error: function (e) {
            $("#result").html('<p>' + e.responseText)
        }


    })

}

