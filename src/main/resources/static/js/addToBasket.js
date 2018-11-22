$(document).ready(function () {

    $("#addTocart").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit()
    })
});

function fire_ajax_submit(){
    var result_div = $("#result");
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
            result_div.attr("class", "alert alert-success");
            result_div.html('<p class="result text-success">Добавлено в корзину')
        },
        error:function(e){
            result_div.attr("class", "alert alert-danger");
            result_div.html('<p class="result text-danger">'+e.responseText)
        }

    })

}