/**
 * Phonegap Push plugin
 *
 */

/**
 *
 * @return Object literal singleton instance of PushPlugin
 */

var PushLoader = function(require, exports, module) {


    var exec = require("cordova/exec");

    console.log('inside push.js');

    function PushNotification() {}


    // Call this to register for push notifications. Content of [options] depends on whether we are working with APNS (iOS) or GCM (Android)
    PushNotification.prototype.register = function(successCallback, errorCallback, options) {
        if (errorCallback === null) {
            errorCallback = function() {};
        }

        if (typeof errorCallback != "function") {
            console.log("PushNotification.register failure: failure parameter not a function");
            return;
        }

        if (typeof successCallback != "function") {
            console.log("PushNotification.register failure: success callback parameter must be a function");
            return;
        }

        exec(successCallback, errorCallback, "PushPlugin", "register", [options]);
    };

    // Call this to unregister for push notifications
    PushNotification.prototype.unregister = function(successCallback, errorCallback) {
        if (errorCallback === null) {
            errorCallback = function() {};
        }

        if (typeof errorCallback != "function") {
            console.log("PushNotification.unregister failure: failure parameter not a function");
            return;
        }

        if (typeof successCallback != "function") {
            console.log("PushNotification.unregister failure: success callback parameter must be a function");
            return;
        }

        exec(successCallback, errorCallback, "PushPlugin", "unregister", []);
    };


    // Call this to set the application icon badge
    PushNotification.prototype.setApplicationIconBadgeNumber = function(successCallback, errorCallback, badge) {
        if (errorCallback === null) {
            errorCallback = function() {};
        }

        if (typeof errorCallback != "function") {
            console.log("PushNotification.setApplicationIconBadgeNumber failure: failure parameter not a function");
            return;
        }

        if (typeof successCallback != "function") {
            console.log("PushNotification.setApplicationIconBadgeNumber failure: success callback parameter must be a function");
            return;
        }

        exec(successCallback, errorCallback, "PushPlugin", "setApplicationIconBadgeNumber", [{
            badge: badge
        }]);
    };

    //-------------------------------------------------------------------

    console.log("ici");
    var pushNotification = new PushNotification();
    module.exports = pushNotification;


    console.log("end inside push.js");
};

PushLoader(require, exports, module);

cordova.define("cordova/plugin/PushPlugin", PushLoader);