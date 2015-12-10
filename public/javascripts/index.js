$(function () {
    if (~window.location.search.indexOf('startTime') && ~window.location.search.indexOf('endTime')) {
        $('input[name=startTime]').val(window.location.search.match(/startTime=([\w-]+)/)[1]);
        $('input[name=endTime]').val(window.location.search.match(/endTime=([\w-]+)/)[1]);
    }
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