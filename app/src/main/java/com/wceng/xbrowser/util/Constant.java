package com.wceng.xbrowser.util;

import android.os.Build;

import com.wceng.xbrowser.BuildConfig;

public class Constant {

    public static String userAgent_windows = "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36";
    public static String userAgent_mac = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0.3 Safari/605.1.15";
    public static String userAgent_iphone = "Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15E148 Safari/604.1";
    public static String userAgent_ipad = "Mozilla/5.0 (iPad; CPU OS 14_0 like Mac OS X) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.72 Safari/537.36";
    public static String userAgent_android = "MyApp/" + BuildConfig.VERSION_NAME + " (Android; " +
            Build.VERSION.RELEASE + "; " + Build.MODEL + ")";

}
