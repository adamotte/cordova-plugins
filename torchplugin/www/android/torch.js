/**
 * Phonegap Torch plugin
 *
 */

/**
 *
 * @return Object literal singleton instance of Torch
 */

var TorchLoader = function(require, exports, module) {

    var exec = require("cordova/exec");

    function Torch() {}

    /**
     * Toggles the torch and sends the new state
     * @param success The callback for success
     * @param error The callback for error
     */
    Torch.prototype.toggle = function(success, error) {
        return exec(function(response) {
            if (response.isOn) {
                success.call(this, true);
            } else {
                success.call(this, false);
            }
        }, error, "Torch", "toggle", []);
    };

    //FIX:MANTIS:2976
    /**
     * @param success The callback for success
     * @param error The callback for error
     */
    Torch.prototype.turnOff = function(success, error) {
        return exec(success, error, "Torch", "turnOff", []);
    };

    //FIX:MANTIS:3012
    /**
     * @param success The callback for success
     * @param error The callback for error
     */
    Torch.prototype.isCapable = function(success, error) {
        return exec(function(response) {
            if (response.capable) {
                success.call(this, true);
            } else {
                success.call(this, false);
            }
        }, error, "Torch", "isCapable", []);
    };

    /**
     * @param success The callback for success
     * @param error The callback for error
     */
    Torch.prototype.isOn = function(success, error) {
        return exec(function(response) {
            if (response.isOn) {
                success.call(this, true);
            } else {
                success.call(this, false);
            }
        }, error, "Torch", "isOn", []);
    };

    var torch = new Torch();
    module.exports = torch;

};

TorchLoader(require, exports, module);

cordova.define("cordova/plugin/Torch", TorchLoader);