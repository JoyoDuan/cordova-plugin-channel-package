var exec = require('cordova/exec');

// 这里的命名ChannelPackagePlugin与plugin.xml无关，用abc都可以，module.exports用于require('')
function ChannelPackagePlugin() {}

ChannelPackagePlugin.prototype.getChannel = function(successCallback, errorCallback, channelKey, defaultChannel) {
	exec(successCallback, errorCallback, "ChannelPackagePlugin", "getChannel", [channelKey, defaultChannel]);
};

module.exports = new ChannelPackagePlugin();
