package com.wceng.xbrowser.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wceng.xbrowser.R;
import com.wceng.xbrowser.databinding.ItemBottomNavViewBinding;
import com.wceng.xbrowser.other.Observer;
import com.wceng.xbrowser.window.Window;
import com.wceng.xbrowser.window.WindowListener;

public class XBottomNavigationView extends BottomNavigationView implements Observer {

    ItemBottomNavViewBinding mBinding;
    private Connector mConnector;

    @Override
    public void onWindowUpdate(Window w) {
        mConnector.connect(w);
    }

    public interface IBottomNavClickListener {

        void onBack(View view);

        void onHome(View view);

        void onTitle(View view);

        void onWindow(View view);

        void onMenu(View view);

    }

    public XBottomNavigationView(@NonNull Context context) {
        super(context);
    }

    public XBottomNavigationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        mConnector = new Connector(this);
    }

    @SuppressLint("InflateParams")
    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        mBinding = DataBindingUtil.inflate(inflater, R.layout.item_bottom_nav_view, null, false);
        addView(mBinding.getRoot());
    }

    public void setEventListener(IBottomNavClickListener bottomNavEventListener) {
        mBinding.materialButtonBack.setOnClickListener(bottomNavEventListener::onBack);
        mBinding.materialButtonHome.setOnClickListener(bottomNavEventListener::onHome);
        mBinding.materialButtonWindow.setOnClickListener(bottomNavEventListener::onWindow);
        mBinding.materialButtonMenu.setOnClickListener(bottomNavEventListener::onMenu);
        mBinding.textViewTitle.setOnClickListener(bottomNavEventListener::onTitle);
    }

    public void updateWindowNumber(int num) {
        mBinding.materialButtonWindow.setText(String.valueOf(num));
    }

    public void updateTitle(String title) {
        mBinding.textViewTitle.setText(title);
    }



    /**
     * window与BottomNavView交互的连接器
     */
    public final static class Connector {
        private final XBottomNavigationView mNavView;
        private Window mWindow;

        public Connector(XBottomNavigationView mNavView) {
            this.mNavView = mNavView;
            navJack();
        }

        private void navJack() {
            mNavView.setEventListener(new IBottomNavClickListener() {
                @Override
                public void onBack(View view) {
                    if (mWindow != null) {
                        if (mWindow.canGoBack()) {
                            mWindow.getPageJumpController().goBack();
                        }
                    }
                }

                @Override
                public void onHome(View view) {
                    if (mWindow != null) {
                        mWindow.getPageJumpController().jumpHome();
                    }
                }

                @Override
                public void onTitle(View view) {
                    if (mWindow != null) {
                        mWindow.getPageJumpController().jumpSearch();
                    }
                }

                @Override
                public void onWindow(View view) {

                }

                @Override
                public void onMenu(View view) {

                }
            });
        }


        public void connect(Window window) {
            mWindow = window;
            windowJack(window);
        }

        public void windowJack(Window window) {
            window.setWindowListener(new WindowListener() {
                @Override
                public void onTitleUpdate(String title) {
                    mNavView.updateTitle(title);
                }

                @Override
                public void onPageNavigationStateChanged(boolean canGoBack, boolean canGoForward) {

                }

                @Override
                public void onIconUpdate(Bitmap bitmap) {

                }
            });
        }

        public void disconnect(Window window) {

        }


    }
}
