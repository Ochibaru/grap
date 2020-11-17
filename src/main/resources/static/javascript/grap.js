$(document).ready(function () {
    $(function() {
        $("#search").autocomplete({
            source: "searchAutocomplete",
            minLength: 3
        });
    });
});