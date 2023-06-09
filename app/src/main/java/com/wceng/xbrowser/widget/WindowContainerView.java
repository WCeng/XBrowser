package com.wceng.xbrowser.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wceng.xbrowser.other.Observable;
import com.wceng.xbrowser.util.WindowManager;

public class WindowContainerView extends FrameLayout implements Observable {

    private WindowManager mWindowManager;

    public WindowContainerView(@NonNull Context context) {
        super(context);
    }

    public WindowContainerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mWindowManager = new WindowManager(getContext(), this);

        initWindow();
    }


    private void initWindow() {
        mWindowManager.addHomeWindow();
    }

    public void addWindow(Window window) {
        addView(window);
    }

    public Window getCurWindow(){
        return mWindowManager.getCurWindow();
    }

}
