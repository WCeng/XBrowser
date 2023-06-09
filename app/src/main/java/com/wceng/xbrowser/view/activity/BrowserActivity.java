package com.wceng.xbrowser.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.wceng.xbrowser.R;
import com.wceng.xbrowser.databinding.ActivityBrowserBinding;
import com.wceng.xbrowser.viewmodel.BrowserViewModel;
import com.wceng.xbrowser.widget.WindowContainerView;
import com.wceng.xbrowser.widget.XBottomNavigationView;
import com.wceng.xbrowser.window.Window;

public class BrowserActivity extends AppCompatActivity implements BrowserController {

    ActivityBrowserBinding mBinding;
    XBottomNavigationView mNavView;
    WindowContainerView mWindowContainer;
    BrowserViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        mWindowContainer.addObserver(mNavView);
        mWindowContainer.init();
    }

    private void initData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_browser);
        mBinding.setLifecycleOwner(this);
        mViewModel = new ViewModelProvider(this).get(BrowserViewModel.class);
        mBinding.setViewModel(mViewModel);

        mNavView = mBinding.navView;
        mWindowContainer = mBinding.windowContainer;
    }

    @Override
    public void onBackPressed() {
        Window.Navigator navigator = mWindowContainer.getCurWindow().getNavigator();
        if (navigator.canGoBack()) {
            navigator.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void enterFullScreen() {
        mBinding.bottomNavContainer.setVisibility(View.GONE);
    }

    @Override
    public void exitFullScreen() {
        mBinding.bottomNavContainer.setVisibility(View.VISIBLE);
    }
}