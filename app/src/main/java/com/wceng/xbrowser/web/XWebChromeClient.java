package com.wceng.xbrowser.web;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class XWebChromeClient extends WebChromeClient {

    private final WebViewListener webViewListener;

    public XWebChromeClient(WebViewListener webViewListener) {
        this.webViewListener = webViewListener;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        webViewListener.onReceivedTitle(view, title);
    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        super.onReceivedIcon(view, icon);
        webViewListener.onReceivedIcon(view, icon);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        webViewListener.onProgressChanged(view, newProgress);
    }

    @Override
    public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
        super.onReceivedTouchIconUrl(view, url, precomposed);
        webViewListener.onReceivedTouchIconUrl(view, url, precomposed);
    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        boolean handled = webViewListener.onShowFileChooser(webView, filePathCallback, fileChooserParams);
        if (!handled) {
            return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
        }
        return true;
    }

    @Override
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        super.onGeolocationPermissionsShowPrompt(origin, callback);
        webViewListener.onGeolocationPermissionsShowPrompt(origin, callback);
    }

    @Override
    public void onGeolocationPermissionsHidePrompt() {
        super.onGeolocationPermissionsHidePrompt();
        webViewListener.onGeolocationPermissionsHidePrompt();
    }

    @Override
    public void onPermissionRequest(PermissionRequest request) {
        super.onPermissionRequest(request);
        webViewListener.onPermissionRequest(request);
    }

    @Override
    public void onPermissionRequestCanceled(PermissionRequest request) {
        super.onPermissionRequestCanceled(request);
        webViewListener.onPermissionRequestCanceled(request);
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        boolean handled = webViewListener.onJsAlert(view, url, message, result);
        if (!handled) {
            return super.onJsAlert(view, url, message, result);
        }
        return true;
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        boolean handled = webViewListener.onJsConfirm(view, url, message, result);
        if (!handled) {
            return super.onJsConfirm(view, url, message, result);
        }
        return true;
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        boolean handled = webViewListener.onJsPrompt(view, url, message, defaultValue, result);
        if (!handled) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }
        return true;
    }

    @Override
    public void onHideCustomView() {
        super.onHideCustomView();
        webViewListener.onHideCustomView();
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        super.onShowCustomView(view, callback);
        webViewListener.onShowCustomView(view, callback);
    }
}
