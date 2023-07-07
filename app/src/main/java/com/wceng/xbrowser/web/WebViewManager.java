package com.wceng.xbrowser.web;

import android.content.Context;

public class WebViewManager {

    private Context mContext;
    private XWebView mWebView;

    public WebViewManager(Context context) {
        this.mContext = context;
        mWebView = XWebView.newInstance(context);
    }

    public XWebView getWebView() {
        return mWebView;
    }

    public void destroy() {
        mContext = null;
        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
    }
}
