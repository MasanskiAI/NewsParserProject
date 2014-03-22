$(document).ready(function() {
    $("#outputTextArea").height($("#outputTextArea")[0].scrollHeight);
})

function adaptiveheight(a) {
    $(a).height(0);
    var scrollval = $(a)[0].scrollHeight;
    $(a).height(scrollval);
    if (parseInt(a.style.height) > $(window).height() - 30) {
        $(document).scrollTop(parseInt(a.style.height));
    }
}