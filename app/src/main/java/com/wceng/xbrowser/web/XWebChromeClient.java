package com.wceng.xbrowser.web;

import static android.content.ContentValues.TAG;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class XWebChromeClient extends WebChromeClient {

    private WebViewListener webViewListener;

    public void setWebViewListener(WebViewListener webViewListener) {
        this.webViewListener = webViewListener;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (webViewListener != null) {
            webViewListener.onReceivedTitle(view, title);
        }
    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        if (webViewListener != null) {
            webViewListener.onReceivedIcon(view, icon);
        }
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (webViewListener != null) {
            webViewListener.onProgressChanged(view, newProgress);
        }
    }

    @Override
    public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
        if (webViewListener != null) {
            webViewListener.onReceivedTouchIconUrl(view, url, precomposed);
        }
    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        if (webViewListener != null) {
            boolean handled = webViewListener.onShowFileChooser(webView, filePathCallback, fileChooserParams);
            if (handled) {
                return true;
            }
        }
        return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
    }

    @Override
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        if (webViewListener != null) {
            webViewListener.onGeolocationPermissionsShowPrompt(origin, callback);
        }
    }

    @Override
    public void onGeolocationPermissionsHidePrompt() {
        if (webViewListener != null) {
            webViewListener.onGeolocationPermissionsHidePrompt();
        }
    }

    @Override
    public void onPermissionRequest(PermissionRequest request) {
        if (webViewListener != null) {
            webViewListener.onPermissionRequest(request);
        }
    }

    @Override
    public void onPermissionRequestCanceled(PermissionRequest request) {
        if (webViewListener != null) {
            webViewListener.onPermissionRequestCanceled(request);
        }
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        if (webViewListener != null) {
            boolean handled = webViewListener.onJsAlert(view, url, message, result);
            if (handled) {
                return true;
            }
        }
        return super.onJsAlert(view, url, message, result);
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        if (webViewListener != null) {
            boolean handled = webViewListener.onJsConfirm(view, url, message, result);
            if (handled) {
                return true;
            }
        }
        return super.onJsConfirm(view, url, message, result);
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        if (webViewListener != null) {
            boolean handled = webViewListener.onJsPrompt(view, url, message, defaultValue, result);
            if (handled) {
                return true;
            }
        }
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

    @Override
    public void onHideCustomView() {
        if (webViewListener != null) {
            webViewListener.onHideCustomView();
        }
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        if (webViewListener != null) {
            webViewListener.onShowCustomView(view, callback);
        }
    }
}
