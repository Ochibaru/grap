$(document).ready(function () {
    $(".image").mouseenter(function(){
        $(this).css( {"opacity":"0.8"} );
    }).mouseout(function(){
        $(this).css( {"opacity":"1.0"} );
    });

    $("#bxslider1, #bxslider2, #bxslider3, #bxslider4").bxSlider({
        minSlides: 1,
        maxSlides: 5,
        mode: 'horizontal',
        adaptiveHeight: false,
        slideWidth: 285,
        moveSlides: 5,
        touchEnabled: false,
        pager: false
    });
});