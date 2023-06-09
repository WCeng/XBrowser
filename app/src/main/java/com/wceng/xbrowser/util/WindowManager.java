package com.wceng.xbrowser.util;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.wceng.xbrowser.view.fragment.HomePage;
import com.wceng.xbrowser.view.fragment.WebPage;
import com.wceng.xbrowser.widget.Window;
import com.wceng.xbrowser.widget.WindowContainerView;

import java.util.ArrayList;
import java.util.List;

public class WindowManager {

    private final Context mContext;
    private final WindowContainerView mWindowContainer;
    private final List<Window> mWindowList;
    private Window mCurrentWindow;
    private final WindowGenerator mWindowGenerator;

    public WindowManager(
            Context context, WindowContainerView windowContainer) {
        mContext = context;
        mWindowContainer = windowContainer;
        mWindowList = new ArrayList<>();
        mWindowGenerator = new WindowGenerator();
    }

    public void addHomeWindow() {
        Window homeWindow = mWindowGenerator.createHomeWindow();
        mWindowList.add(homeWindow);

        mWindowContainer.addWindow(homeWindow);
        showWindow(homeWindow);
    }

    public void addWebWindow(String url) {
        Window webWindow = mWindowGenerator.createWebWindow(url);
        mWindowContainer.addWindow(webWindow);

        mWindowList.add(webWindow);
    }

    private void showWindow(Window w) {
        for (Window window : mWindowList) {
            if (window != w) {
                window.hide();
            }
        }
        w.show();
        mCurrentWindow = w;
    }

    public Window getCurWindow() {
        return mCurrentWindow;
    }

    public int getWindowNum() {
        return mWindowList.size();
    }

    public void onDestroy() {

    }

    /**
     * 窗口生成器
     */
    private final class WindowGenerator {
        public WindowGenerator() {

        }

        public Window createHomeWindow() {
            Window window = new Window(mContext);
            HomePage homePage = window.getPageFactory().createHomePage();
            window.addPage(homePage);
            return window;
        }

        public Window createWebWindow(String url) {
            Window window = new Window(mContext);
            HomePage homePage = window.getPageFactory().createHomePage();
            WebPage webPage = window.getPageFactory().createWebPage();
            window.addPage(homePage);
            window.addPage(webPage);
            return window;
        }
    }

}
