var polling = {
    addUrl: function(config, successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            "PollingPlugin",
            "addUrl",
            [config]
        );
    },
    removeUrl: function(config, successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            "PollingPlugin",
            "removeUrl",
            [config]
        );
    },
    setInterval: function(config, successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            "PollingPlugin",
            "setInterval",
            [config]
        );
    },
    deactivate: function(config, successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            "PollingPlugin",
            "deactivate",
            [config]
        );
    }
};
module.exports = polling;
