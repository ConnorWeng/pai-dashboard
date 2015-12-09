$(function () {
    $(".fa-mouse-pointer").popover({
        container: 'body',
        placement: 'bottom',
        trigger: 'hover',
        html: true,
        content: function () {
            var divId = 'tmp-id-' + $.now();
            return detailsInPopup($(this).attr('pai-app-id'), $(this).attr('pai-page'), divId);
        }
    });
    function detailsInPopup(appId, page, divId) {
        $.get('/' + encodeURIComponent(appId) + '/' + encodeURIComponent(page) + '/events/click', function (d) {
            $('#' + divId).html(d)
        });
        return '<div class="click-list" id="' + divId + '">Loading...</div>';
    }
});