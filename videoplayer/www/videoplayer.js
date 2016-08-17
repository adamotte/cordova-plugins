/**
 * Phonegap VideoPlayer plugin
 *
 */
var PluginLoader = function(require, exports, module) {

    var exec = require("cordova/exec");

    function VideoPlayer() {}

    /**
     * Starts the video player intent
     *
     * @param url           The url to play
     */
    VideoPlayer.prototype.play = function(url) {
        exec(null, null, "VideoPlayer", "playVideo", [url]);
    };

    var videoPlayer = new VideoPlayer();
    module.exports = videoPlayer;

};

PluginLoader(require, exports, module);

cordova.define("cordova/plugin/VideoPlayer", PluginLoader);
