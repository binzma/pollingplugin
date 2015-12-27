var alarm = {
    set: function(alarmDate, successCallback, errorCallback) {
        if(alarmDate < new Date())
    		return;
    	
        cordova.exec(
            successCallback,
            errorCallback,
            "PollingPlugin",
            "programAlarm",
            [alarmDate]
        );
    }
};
module.exports = alarm;
