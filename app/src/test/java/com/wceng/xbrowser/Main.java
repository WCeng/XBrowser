package com.wceng.xbrowser;

import static android.content.ContentValues.TAG;

import android.util.Log;

public class Main {
    private final Class<Inner> innerClass;

    public Main(Class<Inner> innerClass) {
        this.innerClass = innerClass;
    }

    public Class<Inner> get() {
        return innerClass;
    }

    public static class Inner {
        static {
            int i = 10;
        }
    }
}
