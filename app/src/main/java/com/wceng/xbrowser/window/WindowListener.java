package com.wceng.xbrowser.window;

import android.graphics.Bitmap;

/**
 * 窗口事件监听器
 */
public interface WindowListener {
    // 当窗口标题被更新时调用
    void onTitleUpdate(String title);

    // 当前页前进/后退状态发生变化时调用
    void onPageNavigationStateChanged(boolean canGoBack, boolean canGoForward);

    //窗口图标更新时调用
    void onIconUpdate(Bitmap bitmap);
}