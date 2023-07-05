package com.wceng.xbrowser.bean;

import android.graphics.Bitmap;

public class WindowInfo {
    private String title;
    private String url = "https://www.baidu.com";
    private Bitmap bitmap;

    public String getTitle() {
        return title;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void updateUrl(String url) {
        this.url = url;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void updateBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
