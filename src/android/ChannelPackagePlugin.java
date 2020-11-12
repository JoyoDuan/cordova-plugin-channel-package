package com.joyo.cordova.channel;

import android.content.Context;
import android.text.TextUtils;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Locale;

public class ChannelPackagePlugin extends CordovaPlugin {
    // 获取渠道方法名
    private static final String ACTION_GET_CHANNEL = "getChannel";

    // meta-data中的渠道key
    private final static String CHANNEL_KEY_DEFAULT = "JOYO_CHANNEL";

    Context context = null;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        // 上下文
        context = cordova.getActivity().getApplicationContext();

        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (ACTION_GET_CHANNEL.equals(action.toLowerCase(Locale.CHINA))) {
            // 获取渠道的key值
            String channelKey = args.getString(0);
            if (TextUtils.isEmpty(channelKey)) {
                channelKey = CHANNEL_KEY_DEFAULT;
            }

            String channelValue = args.getString(1);
            // 返回结果给Cordova
            callbackContext.success(ChannelUtils.getChannel(context, channelKey, channelValue));
            return true;
        }
        return false;
    }
}
