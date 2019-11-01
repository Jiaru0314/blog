
var windowScroll = function () {
    $(window).scroll(function () {

        var scrollPos = $(this).scrollTop();

        var system = {win: false, mac: false, xll: false};
        var p = navigator.platform;
        system.win = p.indexOf("Win") == 0;
        system.mac = p.indexOf("Mac") == 0;
        system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);
        if ($(window).scrollTop() > 70) {
            $('.site-header').addClass('site-header-nav-scrolled');
            $('.icon-logo').addClass('site-header-nav-scrolled');

        } else {
            $('.site-header').removeClass('site-header-nav-scrolled');
            $('.icon-logo').removeClass('site-header-nav-scrolled');

        }
    });
};

function headerSubmenu() {
    $('.site-header-nav').find('.nav-item').each(function () {
        $(this).hover(function () {
            $(this).find('.submenu').show()
        }, function () {
            $(this).find('.submenu').hide()
        })
    })
};

$(document).ready(function () {
    windowScroll();
    headerSubmenu();
});