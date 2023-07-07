package com.wceng.xbrowser.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;

public class XWebView extends WebView {

    private final WebViewController mWebViewController;
    private XWebViewClient mWebViewClient;
    private XWebChromeClient mWebChromeClient;


    public static XWebView newInstance(Context context) {
        return new XWebView(context);
    }

    public XWebView(@NonNull Context context) {
        super(context);
        initSettings();
        initClient();
        mWebViewController = new WebViewControllerImpl();
    }

    private void initClient() {
        mWebViewClient = new XWebViewClient();
        mWebChromeClient = new XWebChromeClient();

        setWebViewClient(mWebViewClient);
        setWebChromeClient(mWebChromeClient);
    }

    public boolean isShowing() {
        return getParent() != null;
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initSettings() {
        WebSettings settings = getSettings();
        //启用 JavaScript
        settings.setJavaScriptEnabled(true);

        //缩放控制和手势缩放
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        // 适应屏幕大小
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        //处理不安全内容
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        //设置 UserAgent
//        settings.setUserAgentString(Constant.userAgent_android);

        // 启用本地存储特性
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
    }

    public final class WebViewControllerImpl implements WebViewController {

        @Override
        public void setDarkMode(boolean b) {

        }

        @Override
        public void setPrivateMode(boolean b) {

        }
    }

    public WebViewController getWebViewController() {
        return mWebViewController;
    }

    public void setWebViewListener(WebViewListener mWebViewListener) {
        mWebViewClient.setWebViewListener(mWebViewListener);
        mWebChromeClient.setWebViewListener(mWebViewListener);
    }
}
