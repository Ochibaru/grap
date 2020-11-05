$(document).ready(function () {
    $(".image").mouseenter(function(){
        $(this).css( {"opacity":"0.8"} );
    }).mouseout(function(){
        $(this).css( {"opacity":"1.0"} );
    });
});