(function () {

    "use strict";

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

    $('.customerMenuIcon').on('click', function () {
        $('.popUpImgContainer').toggle();
    });

    $('.logOutCustomer').on('click', function(){

    });

})();