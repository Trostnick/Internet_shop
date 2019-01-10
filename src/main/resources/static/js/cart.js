$(document).ready(function () {


    $(".removeOrderCamp").submit(function (event) {
        event.preventDefault();
        ajaxRemove($(this).data("id"));
    });


    $(".count").keyup(function () {
        var campId = $(this).data("id");
        var newCount = $(this).val();
        if ($.isNumeric(newCount) && parseInt(newCount) > 0) {
            if ($(this).val().length>4 || newCount>1000){
                $(this).val(1000);
                newCount=1000;
            }
            $(this).attr("data-count", newCount);
            ajaxChangeCount(campId, newCount)
        } else {
            $(this).val($(this).data("count"))
        }
    }).mouseup(function () {
        var campId = $(this).data("id");
        var newCount = $(this).val();
        $(this).attr("data-count", newCount);
        ajaxChangeCount(campId, newCount)

    })
});


function ajaxRemove(campId) {


    $.ajax({

        type: "DELETE",
        url: '/api/orderPart/' + campId,
        success: function () {
            var curOrderPrice = $("#orderPrice");
            var orderPrice = curOrderPrice.text().replace(/\s+/g, '');
            orderPrice = parseInt(orderPrice);
            var curOrderPart = $("#orderPart-" + campId);
            var campPrice = curOrderPart.find(".price").text().replace(/\s+/g, '');
            campPrice = parseInt(campPrice);
            var campCount = curOrderPart.find(".count").val();
            campCount = parseInt(campCount);
            orderPrice -= campPrice * campCount;
            if (orderPrice > 0) {
                curOrderPrice.html(orderPrice.toLocaleString());
                curOrderPart.remove();
            } else {
                location.reload(true);
            }
        }
    })
}


function ajaxChangeCount(campId, newCount) {
    var params = {};
    params.newCount = newCount;
    $.ajax({
        type: "PATCH",
        url: "/api/orderPart/" + campId,
        contentType: "application/json",
        data: JSON.stringify(params),

        success: function () {
            var orderPrice = 0;
            $(".orderPart").each(function () {
                var campPrice = $(this).find(".price").text().replace(/\s+/g, '');
                campPrice = parseInt(campPrice);
                var campCount = $(this).find(".count").val();
                campCount = parseInt(campCount);
                orderPrice += campPrice * campCount;

            });
            $("#orderPrice").html(orderPrice.toLocaleString());

        }

    })
}