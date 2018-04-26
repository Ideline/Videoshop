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

            cancel: {
                text: 'Avbryt',
                keys: ['esc'],
                action: function () {
                    return true;
                }
            },
            delete: {
                text: 'Ta bort',
                keys: ['enter'],
                action: function () {
                    location.href = this.$target.attr('href');
                }
            }
        }

    });

    // $('#rentButton').on('click', function (e) {
    //
    //     e.stopPropagation();
    //     e.preventDefault();
    //
    //     if(notAvailable){
    //         $.alert({
    //             useBootstrap: false,
    //             columnClass: 'small',
    //             animationBounce: 2.0,
    //             closeAnimation: 'scale',
    //             type: 'orange',
    //             theme: 'material',
    //             title: 'VARNING!',
    //             content: 'FILMEN ÄR REDAN UTHYRD',
    //         });
    //     }
    //     else if(notExist){
    //         $.alert({
    //             useBootstrap: false,
    //             columnClass: 'small',
    //             animationBounce: 2.0,
    //             closeAnimation: 'scale',
    //             type: 'orange',
    //             theme: 'material',
    //             title: 'VARNING!',
    //             content: 'FELAKTIGT FILM-ID',
    //         });
    //     }
    //     else {
    //         $.confirm({
    //             title: 'BEKRÄFTA',
    //             content: 'BILD HÄR OCH FILMTITEL',
    //             buttons: {
    //                 confirm: function () {
    //                     $.alert('Confirmed!');
    //                 },
    //                 cancel: function () {
    //                     $.alert('Canceled!');
    //                 },
    //                 somethingElse: {
    //                     text: 'Something else',
    //                     btnClass: 'btn-blue',
    //                     keys: ['enter', 'shift'],
    //                     action: function(){
    //                         $.alert('Something else?');
    //                     }
    //                 }
    //             }
    //         });
    //     }
    //
    //
    //
    // });





    $('.customerMenuIcon').on('click', function () {
        $('.popUpImgContainer').toggle();
    });

    $('.logOutCustomer').on('click', function(){

    });

})();