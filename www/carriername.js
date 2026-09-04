var exec = require('cordova/exec');

var CarrierName = {

    getCarrierName: function (success, error) {

        exec(
            success,
            error,
            'CarrierName',
            'getCarrierName',
            []
        );

    }

};

module.exports = CarrierName;
