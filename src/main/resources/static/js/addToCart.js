$(document).ready(function () {

    $("#addToCart").submit(function (event) {
        event.preventDefault();
        fireAjaxSubmit()
    });

    $("#count").keyup(function () {
        var newCount = $(this).val();
        if ($.isNumeric(newCount) && parseInt(newCount) > 0) {
            if ($(this).val().length>4 || newCount>1000){
                $(this).val(1000);
                newCount=1000;
            }
            $(this).attr("data-count", newCount);
        } else {
            $(this).val($(this).data("count"))
        }
    })
});

function fireAjaxSubmit() {
    var resultDiv = $("#result");
    params = {};
    params["camp"] = {};
    params["camp"]["id"] = campId;
    params["count"] = $("#count").val();

    $.ajax({
        type: "POST",
        url: "/api/orderPart",
        data: JSON.stringify(params),
        contentType: "application/json",
        dataType: "json",
        success: function () {
            resultDiv.attr("class", "alert alert-success");
            resultDiv.html('<p class="result text-success">Добавлено в корзину')
        },
        error: function (e) {
            resultDiv.attr("class", "alert alert-danger");
            resultDiv.html('<p class="result text-danger">' + e.responseText)
        }

    })

}