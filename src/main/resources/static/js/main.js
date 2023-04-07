let index = 0;
let list = $('.slide');
list.each(function () {
    $(this).hide();
});

function slideShow() {
    if (index != 0) list.eq(index - 1).hide();
    if (index == list.length) index = 0;

    list.eq(index).show();
    index++;
    setTimeout(slideShow, 2000);
}

slideShow();