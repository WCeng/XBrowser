package com.wceng.xbrowser.base;

import androidx.fragment.app.Fragment;

import com.wceng.xbrowser.widget.Window;
import com.wceng.xbrowser.util.WindowManager;
import com.wceng.xbrowser.view.activity.BrowserActivity;
import com.wceng.xbrowser.view.activity.BrowserController;


public class BasePage extends Fragment {

    private Window mWindow;

    public void setWindow(Window w) {
        this.mWindow = w;
    }

//    public WindowManager getWindowManager(){
//        return ((BrowserActivity)requireActivity()).getWebWindowManager();
//    }

    public Window getWindow(){
        return mWindow;
    }

    public BrowserController getBrowserController(){
        return ((BrowserActivity) requireActivity());
    }

}
