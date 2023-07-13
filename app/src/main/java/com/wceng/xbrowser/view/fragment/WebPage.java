package com.wceng.xbrowser.view.fragment;

import static android.content.ContentValues.TAG;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ClientCertRequest;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.wceng.xbrowser.R;
import com.wceng.xbrowser.base.BasePage;
import com.wceng.xbrowser.databinding.FragmentWebBinding;
import com.wceng.xbrowser.web.WebViewListener;
import com.wceng.xbrowser.web.XWebView;
import com.wceng.xbrowser.window.Window;

public class WebPage extends BasePage implements WebViewListener {

    private FragmentWebBinding mBinding;
    private XWebView mWebView;

    public static WebPage newInstance(Window window) {
        WebPage fragment = new WebPage();
        fragment.setWindow(window);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_web, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mWebView == null) {
            mWebView = getWindow().getWebView();
            mBinding.webViewContainer.addView(mWebView);
            initWebView();
        }
    }

    public void initWebView() {
        mWebView.setWebViewListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
            mWebView.resumeTimers();
            Log.e(TAG, "onResume: " );
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
            mWebView.pauseTimers();
            Log.e(TAG, "onPause: " );
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.setWebViewListener(null);
            mBinding.webViewContainer.removeAllViews();
            mWebView = null;
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(WebView view, String url) {

    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {

    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        mBinding.processBar.setProgress(newProgress, true);
        if(newProgress == 100){
            mBinding.processBar.setVisibility(View.GONE);
            mBinding.processBar.setProgress(0);
        }else {
            mBinding.processBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        getWindow().getInfoUpdater().updateTitle(title);
    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {

    }

    @Override
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {

    }

    @Override
    public void onPermissionRequest(PermissionRequest request) {

    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return false;
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        return false;
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        return false;
    }

    @Override
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {

    }

    @Override
    public void onHideCustomView() {

    }

    @Override
    public void onPermissionRequestCanceled(PermissionRequest request) {

    }

    @Override
    public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {

    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        return false;
    }

    @Override
    public void onGeolocationPermissionsHidePrompt() {

    }

    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {

    }

    @Override
    public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {

    }

    @Override
    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        return false;
    }
}
