<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${camp.name}</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script type="text/javascript" src="/webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/popper.js/1.14.3/popper.min.js"></script>
    <script type="text/javascript" src="/webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <style>
        /* Make the image fully responsive */
        .carousel-inner img {
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
<div class="container">
    <br>
    <h1> ${camp.name}</h1>
    <br>
    <div class="row">
        <div class="col-3">
<#if camp.icon??>
    <img src="/api/camp/icon/${camp.id}" alt="i" height="150" width="150">
</#if>
        </div>
        <div class="col-6">
            <ul>
            <li>Цена - ${camp.price}
                <#if camp.place??>
            <li>Место проведения                - ${camp.place.name}
                </#if>
                <#if camp.type??>
            <li>Форма проведения - ${camp.type.name}
                </#if>
                <li>Дата начала - ${camp.dateStart}
                <li>Дата окончания - ${camp.dateFinish}
                <li>Минимальный возраст ребенка - ${camp.ageMin}
                <li>Максимальный возраст ребенка - ${camp.ageMax}
            <li>Количество детей - ${camp.childrenCount}
                <#if camp.info??>
            <li> Дополнительная информация - ${camp.info}
                </#if>
            </ul>
        </div>
    </div>
    <#if camp.place??>
        <h2>О месте проведения</h2>
        <div>

        <p>Название - ${camp.place.name}
            <#if camp.place.address??>
            <p>Адрес                        - ${camp.place.address}
            </#if>
            <#if camp.place.info??>
            <p>Дополнительная информация - ${camp.place.info}
            </#if>

        </div>
    </#if>
    <#if firstPhotoId??>
    <h2>Галерея</h2>
    <div id="demo" class="carousel slide" data-ride="carousel">

        <!-- Indicators -->
        <ul class="carousel-indicators">
            <li data-target="#demo" data-slide-to="0" class="active"></li>
            <#if idList??>
                <#list idList as id>
            <li data-target="#demo" data-slide-to="${id}"></li>
                </#list>
            </#if>
        </ul>

        <!-- The slideshow -->
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="/api/photo/${firstPhotoId}" width="1100" height="500">
            </div>

        <#if photoList??>
            <#list photoList as photoId>
                <div class="carousel-item">
                    <img src="/api/photo/${photoId}" width="1100" height="500">
                </div>
            </#list>
        </#if>
        </div>
        <!-- Left and right controls -->
        <#if firstPhotoId??>
            <a class="carousel-control-prev" href="#demo" data-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </a>
            <a class="carousel-control-next" href="#demo" data-slide="next">
                <span class="carousel-control-next-icon"></span>
            </a>
        </#if>

    </div>
    </#if>

    <br>
    <form action="/cart/${camp.id}">
        <button class="btn btn-success"> Добавить в корзину</button>
    </form>
    <br>
    <form action="/home">
        <button class="btn btn-info">На главную</button>
    </form>
    <br>
</div>
</body>
</html>
