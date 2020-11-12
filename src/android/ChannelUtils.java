package com.joyo.cordova.channel;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.text.TextUtils;


/**
 * 渠道工具类
 *
 * @Author JoyoDuan
 * @Date 2020/11/11
 * @Description:
 */
public class ChannelUtils {

    /**
     * 获取渠道
     *
     * @Author JoyoDuan
     * @Date 2020/11/11
     * @Description:
     */
    public static String getChannel(Context context, String channelKey) {
        return getChannel(context, channelKey, "");
    }

    /**
     * 获取渠道
     *
     * @Author JoyoDuan
     * @Date 2020/11/11
     * @Description:
     */
    public static String getChannel(Context context, String channelKey, String defaultChannel) {
        String channel = getChannelBySharedPreferences(context, channelKey);
        if (!TextUtils.isEmpty(channel)){
            return channel;
        }

        channel = getChannelByMetaData(context, channelKey);
        if (!TextUtils.isEmpty(channel)){
            // 保存sp中备用
            setChannelBySharedPreferences(context, channelKey, channel);
            return channel;
        }

        // 全部获取失败
        return defaultChannel;
    }

    /**
     * 获取渠道
     *
     * @Author JoyoDuan
     * @Date 2020/11/11
     * @Description:
     */
    public static String getChannelByMetaData(Context context, String channelKey) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo appInfo = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            // key为<meta-data>标签中的name
            String channel = appInfo.metaData.getString(channelKey);
            if (!TextUtils.isEmpty(channel)) {
                return channel;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将渠道保存到SharedPreferences
     *
     * @Author JoyoDuan
     * @Date 2020/11/11
     * @Description:
     */
    public static void setChannelBySharedPreferences(Context context, String channelKey, String channel) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(channelKey, channel);
        editor.apply();
    }

    /**
     * 从SharedPreferences中获取保存的渠道
     *
     * @Author JoyoDuan
     * @Date 2020/11/11
     * @Description:
     */
    public static String getChannelBySharedPreferences(Context context, String channelKey) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(channelKey, "");
    }
}
