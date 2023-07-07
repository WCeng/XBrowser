package com.wceng.xbrowser.bean;

import android.graphics.Bitmap;

public class WindowInfo {
    private String title;
    private String url;
    private Bitmap bitmap;
    private boolean isPrivate;

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

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
