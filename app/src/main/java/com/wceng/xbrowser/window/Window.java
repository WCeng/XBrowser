package com.wceng.xbrowser.window;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.fragment.app.Fragment;

import com.wceng.xbrowser.bean.WindowInfo;
import com.wceng.xbrowser.view.fragment.PageFactory;
import com.wceng.xbrowser.view.fragment.PageFactoryImpl;
import com.wceng.xbrowser.web.XWebView;
import com.wceng.xbrowser.widget.PageContainer;

import org.jetbrains.annotations.NotNull;

@SuppressLint("ViewConstructor")
public class Window extends PageContainer {
    public static final String homePageHtml = "file:///android_asset/homePage.html";

    private final PageJumpController mPageJumpController;
    private final PageFactory mPageFactory;
    private final WindowInfo mWindowInfo;
    private final XWebView mWebView;
    private final Navigator mNavigator;
    private final InfoUpdater mInfoUpdater;
    private boolean isSearchPageShowing;

    public Window(@NotNull Context context) {
        super(context);
        mPageJumpController = new PageJumpController();
        mPageFactory = new PageFactoryImpl(this);
        mWindowInfo = new WindowInfo();
        mWebView = XWebView.newInstance(context);
        mNavigator = new Navigator();
        mInfoUpdater = new InfoUpdater(this);

        initPage();
    }

    //把所有page添加到Window里并隐藏， 以便后续操作
    private void initPage() {
        addPage(mPageFactory.getSingleHomePage());
        addPage(mPageFactory.getSingleSearchPage());
        addPage(mPageFactory.getSingleWebPage());
        for (Fragment page : getAllPages()) {
            hidePage(page);
        }
    }

    /**
     * 控制window里的页面跳转
     */
    public class PageJumpController {
        public void jumpHome() {
            //已在主页， 跳过；
            if (Window.homePageHtml.equals(mWebView.getUrl())) return;

            mWebView.loadUrl(homePageHtml);
            Window.this.showOnlyPage(mPageFactory.getSingleHomePage());
        }

        public void jumpSearch() {
            Window.this.showOnlyPage(mPageFactory.getSingleSearchPage());
            isSearchPageShowing = true;
        }

        public void exitSearch() {
            Fragment preDisplayedPage = getPreDisplayedPage();
            showOnlyPage(preDisplayedPage);
            isSearchPageShowing = false;
        }

        public void jumpWeb(String url) {
            exitSearch();

            mWebView.loadUrl(url);
            Window.this.showOnlyPage(mPageFactory.getSingleWebPage());
        }

    }

    /**
     * Window前进后退操作导航器
     */
    public class Navigator {

        public boolean canGoBack() {
            if (isSearchPageShowing) return true;
            else return mWebView.canGoBack();
        }

        public boolean canGoForward() {
            return mWebView.canGoForward();
        }

        public void goBack() {

            if (isSearchPageShowing) {
                getPageJumpController().exitSearch();
                return;
            }

            String preUrl =
                    mWebView.getHistoryItemAtIndex(mWebView.getCurrentIndex() - 1).getUrl();
            if (preUrl.equals(Window.homePageHtml)) {
                showOnlyPage(mPageFactory.getSingleHomePage());
            } else {
                showOnlyPage(mPageFactory.getSingleWebPage());
            }

            mWebView.goBack();
        }

        public void goForward() {

        }
    }






    // 以下是对外暴露接口
    public PageJumpController getPageJumpController() {
        return mPageJumpController;
    }

    public WindowInfo getWindowInfo() {
        return mWindowInfo;
    }

    public XWebView getWebView() {
        return mWebView;
    }

    public Navigator getNavigator() {
        return mNavigator;
    }

    public InfoUpdater getInfoUpdater() {
        return mInfoUpdater;
    }

    public void setWindowListener(WindowListener windowListener) {
        mInfoUpdater.setWindowListener(windowListener);
    }



}