var alarm = {
    set: function(config, successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            "PollingPlugin",
            "programAlarm",
            [config]
        );
    }
};
module.exports = alarm;
