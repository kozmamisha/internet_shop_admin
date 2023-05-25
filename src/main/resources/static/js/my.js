$(document).ready(() => {
    $('.minus').click(function () {
        var input = $(this).parent().find('input');
        var count = parseInt(input.val()) - 1;

        count = count < 1 ? 1 : count;

        input.val(count);
        input.change();

        //$(this).parent().find('.my_text').text('minus');

        return false;
    });

    $('.plus').click(
        function () {
            var input = $(this).parent().find('input');
            var count = parseInt(input.val()) + 1;

            input.val(count);
            input.change();

            //$(this).parent().find('.my_text').text('plus');

            return false;
        });

});