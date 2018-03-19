;(function( $ ){

    $.fn.money = function (number, format) {
        var $this = this;
        if (number != 0 && number != '0' && number != '' && number != null && $.isNumeric(number)) {

            menor = false;

            if (format.round) { number = Math.round(number) }

            if (number < 0) {
                menor = true;
                number = Math.abs(number);
            }
            
            if (!number || typeof (number) === 'object') {
                //incase just parameters are entered and not a number
                var format = number;
                number = $this.html();
            }


            var format = format || {},
                commas = format.commas || true,
                symbol = format.symbol || "$";

            number = parseFloat(number, 10)
            .toFixed(2);

            if (commas) {

                var count = 0;
                var numArr = number.toString().split("");

                var len = numArr.length - 6;

                for (var i = len; i > 0; i = i - 3) {
                    numArr.splice(i, 0, ",");

                }

                number = numArr.join("");

            }

            if (typeof symbol === 'string') {
                if (menor) {
                    number = symbol + '-' + number;
                }
                else { number = symbol + number; }

            }

            $this.html(number);

            return number;
        }
        else {
            //$this.html('0');
            //return $this;
            
            //modificado por Alicia 25-04-2017
            $this.html('$0,00');
            return '$0,00';
        }
    };
    
    
})( jQuery );
