let menuInfo = $('#menu-info');
let menuSearch = $('#menu-search');
let menuInfoSub = $(menuInfo).children('ul');
let menuSearchSub = $(menuSearch).children('ul');

$(menuInfoSub).hide();
$(menuSearchSub).hide();

$(menuInfo).hover(function() {

}, function() {

});

$(menuSearch).hover(() => {
    $(menuSearchSub).fadeIn(300);
}, () => {
    $(menuSearchSub).fadeOut(300);
});