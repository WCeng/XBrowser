package com.wceng.xbrowser.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.wceng.xbrowser.R;
import com.wceng.xbrowser.base.BasePage;
import com.wceng.xbrowser.databinding.FragmentWebBinding;
import com.wceng.xbrowser.web.XWebView;
import com.wceng.xbrowser.widget.Window;

public class WebPage extends BasePage {
    public static final String TEXT_SEARCH_CONTENT = "text_search_content";

    private FragmentWebBinding mBinding;
    private XWebView mWebView;

    @Nullable
    private String searchContent;

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
        parseSearchContent();
        initWebView();
        initView();

    }

    private void initWebView() {
        mWebView = XWebView.newInstance(requireContext());
        mWebView.loadUrl(searchContent);
    }

    private void initView() {
        mBinding.webViewContainer.addView(mWebView);
    }

    private void parseSearchContent() {
        if (getArguments() != null) {
            searchContent = getArguments().getString(TEXT_SEARCH_CONTENT);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mWebView != null){
            mWebView.destroy();
            mWebView = null;
        }
    }

    public boolean canGoBack(){
        if(mWebView != null){
            return mWebView.canGoBack();
        }
        return false;
    }

    public void goBack(){
        if(mWebView != null){
            mWebView.goBack();
        }
    }

    public XWebView getWebView() {
        return mWebView;
    }
}
