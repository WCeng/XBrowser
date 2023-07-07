package com.wceng.xbrowser.window;

import android.content.Context;

import com.wceng.xbrowser.view.fragment.HomePage;
import com.wceng.xbrowser.view.fragment.WebPage;
import com.wceng.xbrowser.widget.WindowContainerView;

import java.util.ArrayList;
import java.util.List;

public class WindowManager {

    private final Context mContext;
    private final WindowContainerView mWindowContainer;
    private final List<Window> mWindowList;
    private Window mCurrentWindow;
    private final WindowGenerator mWindowGenerator;
    private IWindowSwitchListener windowSwitchListener;

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
        switchWindow(homeWindow);
    }

    private void switchWindow(Window w) {
        for (Window window : mWindowList) {
            if (window != w) {
                window.hide();
            }
        }
        w.show();
        mCurrentWindow = w;

        if(windowSwitchListener != null){
            windowSwitchListener.onSwitch(w);
        }
    }

    public Window getCurWindow() {
        return mCurrentWindow;
    }

    public void setWindowSwitchListener(IWindowSwitchListener windowSwitchListener) {
        this.windowSwitchListener = windowSwitchListener;
    }

    public int getWindowNum() {
        return mWindowList.size();
    }

    public void onDestroy() {

    }

    public interface IWindowSwitchListener{
        void onSwitch(Window window);
    }

    /**
     * 窗口生成器
     */
    private final class WindowGenerator {
        public WindowGenerator() {

        }

        public Window createHomeWindow() {
            Window window = new Window(mContext);
            window.getPageJumpController().jumpHome();
            return window;
        }

    }

}
