(function () {

    "use strict";

    //=================================================================================================================================================
    //                                                      Kod för slidsen på indexsidan
    //=================================================================================================================================================


    var slideIndex = 0;
    showSlides();

    function showSlides() {
        var i;
        var slides = $('.slideshow');
        if (slides.length > 0) {
            var dots = $('.dot');
            for (i = 0; i < slides.length; i++) {
                $(slides[i]).hide();
            }
            slideIndex++;
            if (slideIndex > slides.length) { slideIndex = 1 }
            for (i = 0; i < dots.length; i++) {
                $(dots[i]).removeClass('active2');
            }
            $(slides[slideIndex - 1]).show();
            $(dots[slideIndex - 1]).addClass('active2');
            setTimeout(showSlides, 3500); // Byter bild var 3,5 sekund
        }
    }


    $('.deleteButton').confirm({
        useBootstrap: false,
        columnClass: 'small',
        animationBounce: 2.0,
        closeAnimation: 'scale',
        type: 'orange',
        theme: 'material',
        title: 'Bekräfta!',
        content: "Är du säker på att du vill ta bort detta filmexemplaret",
        buttons: {

            delete: {
                btnClass: 'btn-orange',
                text: 'Ta bort',
                keys: ['enter'],
                action: function () {
                    location.href = this.$target.attr('href');
                }
            },
            cancel: {
                text: 'Avbryt',
                keys: ['esc'],
                action: function () {
                    return true;
                }
            }
        }

    });

    $('.customerMenuIcon').on('click', function () {
        $('.popUpImgContainer').toggle();
    });

    $('.logOutCustomer').on('click', function(){

    });

})();

function blabla(available, exists, imageUrl, title, filmID) {

    // e.stopPropagation();
    // e.preventDefault();

    if(exists) {
        if(!available){
            $.alert({
                useBootstrap: false,
                columnClass: 'small',
                animationBounce: 2.0,
                closeAnimation: 'scale',
                type: 'orange',
                theme: 'material',
                title: 'VARNING!',
                content: 'FILMEN ÄR REDAN UTHYRD',
            });
        } else {
            $.confirm({
                useBootstrap: false,
                columnClass: 'small',
                animationBounce: 2.0,
                closeAnimation: 'scale',
                type: 'orange',
                theme: 'material',
                title: 'BEKRÄFTA',
                content: '<div class="just-flex"><img class="confirm-img" src="' + imageUrl + '" /><span class="confirm-title"><div>' + title + '</div></span></div>' +
                '<form method="POST" action="/rentalInfo/rentFilm">' +
                '    <input type="hidden" value="' + filmID + '" id="filmID3" name="filmID3">' +
                '</form>'
                ,
                buttons: {
                    formSubmit: {
                        text: 'OK',
                        btnClass: 'btn-orange',
                        action: function () {
                            debugger;
                            this.$content.find('form').trigger('submit');
                        }
                    },
                    cancel: function () {
                        text: 'AVBRYT'
                    }
                }
            });
        }
    } else {
        $.alert({
            useBootstrap: false,
            columnClass: 'small',
            animationBounce: 2.0,
            closeAnimation: 'scale',
            type: 'orange',
            theme: 'material',
            title: 'VARNING!',
            content: 'FELAKTIGT FILM-ID',
        });
    }
}