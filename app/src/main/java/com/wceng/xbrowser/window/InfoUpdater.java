package com.wceng.xbrowser.window;

import android.graphics.Bitmap;

import com.wceng.xbrowser.bean.WindowInfo;

public class InfoUpdater {

    WindowInfo windowInfo;
    private WindowListener mWindowListener;


    public InfoUpdater(Window window) {
        this.windowInfo = window.getWindowInfo();
    }

    public void setWindowListener(WindowListener mWindowListener) {
        this.mWindowListener = mWindowListener;
    }

    public void updateTitle(String title) {
        windowInfo.setTitle(title);
        if (mWindowListener != null)
            mWindowListener.onTitleUpdate(title);
    }

    public void updateFavicon(Bitmap bitmap) {
        windowInfo.setBitmap(bitmap);
        if (mWindowListener != null)
            mWindowListener.onIconUpdate(bitmap);
    }
}
