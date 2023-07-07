package com.wceng.xbrowser.window;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.wceng.xbrowser.bean.WindowState;
import com.wceng.xbrowser.bean.WindowInfo;
import com.wceng.xbrowser.view.fragment.PageFactory;
import com.wceng.xbrowser.view.fragment.PageFactoryImpl;
import com.wceng.xbrowser.view.fragment.WebPage;
import com.wceng.xbrowser.web.WebViewManager;
import com.wceng.xbrowser.widget.PageContainer;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("ViewConstructor")
public class Window extends PageContainer {

    private final PageJumpController mPageJumpController;
    private WindowListener mWindowListener;
    private final PageFactory mPageFactory;
    private final WindowInfo mWindowInfo;
    private final WebViewManager mWebViewManager;
    private final BackStack mBackStack;

    public Window(@NotNull Context context) {
        super(context);
        mPageJumpController = new PageJumpController();
        mPageFactory = new PageFactoryImpl(this);
        mWindowInfo = new WindowInfo();
        mWebViewManager = new WebViewManager(context);
        mBackStack = new BackStack(this);
        initPage();
    }

    //把所有page添加到Window里并隐藏， 以便后续操作
    private void initPage() {
        addPage(mPageFactory.getSingleHomePage());
        addPage(mPageFactory.getSingleSearchPage());
        addPage(mPageFactory.getSingleWebPage());
        for (Fragment page: getAllPages()){
            hidePage(page);
        }
    }

    //保存当前Window的状态
    public void saveStateToBackStack(){
        WindowState windowState = new WindowState(
                getShowingPage(),
                getWindowInfo().getUrl(),
                getWindowInfo().getTitle());
        mBackStack.add(windowState);
    }

    public PageJumpController getPageJumpController() {
        return mPageJumpController;
    }

    public boolean canGoBack() {
        return !mBackStack.isEmpty();
    }

    public WindowInfo getWindowInfo() {
        return mWindowInfo;
    }

    public void setWindowListener(WindowListener windowListener) {
        mWindowListener = windowListener;
    }

    /**
     * 控制window里的页面跳转
     */
    public class PageJumpController {
        public void jumpHome() {
            saveStateToBackStack();
            showPage(mPageFactory.getSingleHomePage());
               //添加到返回栈
//            mBackEventStack.add(new BackEvent(homePage));
        }

        public void jumpSearch() {
            showPage(mPageFactory.getSingleSearchPage());
        }

        public void jumpWeb(String url) {
            getWindowInfo().updateUrl(url);

            //进入web之前先移除SearchPage
            WebPage webPage = mPageFactory.getSingleWebPage();
            showPage(webPage);

            webPage.setWebView(mWebViewManager.getWebView());

            addPage(webPage);
            showPage(webPage);
        }

        public void goBack() {
            WindowState windowState = mBackStack.popup();

            showPage(windowState.getPage());
        }

        private void showPage(Fragment page) {
            List<Fragment> allPages = getAllPages();
            for (Fragment fragment : allPages) {
                if (fragment != page)
                    hidePage(fragment);
            }
            Window.this.showPage(page);
        }
    }

}