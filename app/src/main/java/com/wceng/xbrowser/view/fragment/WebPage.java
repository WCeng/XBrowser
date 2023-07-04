package com.wceng.xbrowser.view.fragment;

import static android.content.ContentValues.TAG;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.wceng.xbrowser.R;
import com.wceng.xbrowser.base.BasePage;
import com.wceng.xbrowser.databinding.FragmentWebBinding;
import com.wceng.xbrowser.web.WebViewListener;
import com.wceng.xbrowser.web.WebViewListenerAdapter;
import com.wceng.xbrowser.web.XWebView;
import com.wceng.xbrowser.widget.Window;

public class WebPage extends BasePage {

    private FragmentWebBinding mBinding;
    private XWebView mWebView;

    public static WebPage newInstance(Window window) {
        WebPage fragment = new WebPage();
        fragment.setWindow(window);
        return fragment;
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
        initWebView();
    }

    private void initWebView() {
        mWebView = XWebView.newInstance(requireContext());
        mWebView.setWebViewListener(webViewListener);
        mWebView.loadUrl(getWindow().getWindowInfo().getUrl());
        mBinding.webViewContainer.addView(mWebView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
    }

    public boolean canGoBack() {
        if (mWebView != null) {
            return mWebView.canGoBack();
        }
        return false;
    }

    public void goBack() {
        if (mWebView != null) {
            mWebView.goBack();
        }
    }

    private final WebViewListener webViewListener = new WebViewListenerAdapter() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.e(TAG, "onPageStarted: "+url );
        }

        @Override
        public void onPermissionRequest(PermissionRequest request) {
            super.onPermissionRequest(request);
        }
    };

}
