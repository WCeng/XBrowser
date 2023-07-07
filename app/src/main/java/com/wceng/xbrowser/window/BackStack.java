package com.wceng.xbrowser.window;

import com.wceng.xbrowser.bean.WindowState;

import java.util.ArrayList;
import java.util.List;

public class BackStack {

    private final List<WindowState> windowStateList;
    private final Window mWindow;

    public BackStack(Window window) {
        mWindow = window;
        windowStateList = new ArrayList<>();
    }

    public void add(WindowState windowState) {
        windowStateList.add(windowState);
    }

    public WindowState popup() {
        if (!isEmpty()) {
            return windowStateList.remove(windowStateList.size() - 1);
        }
        return null;
    }

    public boolean isEmpty() {
        return windowStateList.size() == 0;
    }
}
