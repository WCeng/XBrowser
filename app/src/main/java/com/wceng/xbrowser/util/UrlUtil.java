package com.wceng.xbrowser.util;

public class UrlUtil {
    public static boolean isUrl(String url){
        return url.matches("(https?://)?([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?|([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]{2,}(/[\\w- ./?%&=]*)?");
    }
}
