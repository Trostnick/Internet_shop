$(document).ready(function () {

    $(".removeOrderCamp").submit(function (event) {
        event.preventDefault();
        ajaxRemove($(this).data("id"));
    })
});

function ajaxRemove(campId){


    $.ajax({

        type:"DELETE",
        url: '/api/orderCamp/'+campId,
        success:function () {
            var curOrderPrice = $("#orderPrice");
            var orderPrice = curOrderPrice.text().replace(/\s+/g,'');
            orderPrice = parseInt(orderPrice);
            var curOrderCamp = $("#orderCamp-"+campId);
            var campPrice = curOrderCamp.find(".price").text().replace(/\s+/g,'');
            campPrice = parseInt(campPrice);
            var campCount =curOrderCamp.find(".count").text().replace(/\s+/g,'');
            campCount = parseInt(campCount);
            orderPrice -= campPrice*campCount;
            if (orderPrice > 0) {
                curOrderPrice.html(orderPrice);
                curOrderCamp.remove();
            }else{

            }
        }
    })
}