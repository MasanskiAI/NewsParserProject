$(document).ready(function() {
    /*adaptiveheight($("#templateField"));*/

    $("#templateField").on('keydown keyup', function (e) {
        adaptiveheight(this);
    });

    $("#profileSelect").on('change', function (e) {
        document.location = window.location.pathname + 'profile/' + this.options[this.selectedIndex].value;
    })
})


