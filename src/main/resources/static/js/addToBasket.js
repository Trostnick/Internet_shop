$(document).ready(function () {

    $("#addToBasket").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit()
    })
});

function fire_ajax_submit(){
    params = {};
    params["camp"]={};
    params["camp"]["id"]=campId;
    params["count"]=$("#count").val();

    $.ajax({
        type: "POST",
        url:"/api/orderCamp",
        data: JSON.stringify(params),
        contentType:"application/json",
        dataType:"json",
        success:function () {
            $("#result").html('<p>Добавлено в корзину')
        },
        error:function(e){
            $("#result").html('<p>'+e.responseText)
        }

    })

}