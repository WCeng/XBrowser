package com.wceng.xbrowser.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyboardUtils {

    /**
     * 调起软键盘
     *
     * @param context 上下文对象
     * @param view    EditText 对象或者其他可以获取焦点的 View 对象
     */
    public static void showKeyboard(Context context, View view) {
        if(!view.hasFocus()){
            view.requestFocus();
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param context 上下文对象
     * @param view    EditText 对象或者其他可以获取焦点的 View 对象
     */
    public static void hideKeyboard(Context context, View view) {
        if(view.hasFocus()){
            view.clearFocus();
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
