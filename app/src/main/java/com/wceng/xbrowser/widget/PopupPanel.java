package com.wceng.xbrowser.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public class PopupPanel extends PopupWindow {
    private View contentView;
    private Context context;
    private int layoutRes;
    private int height;

    public PopupPanel(Context context, int layoutRes, int height) {
        this.context = context;
        this.layoutRes = layoutRes;
        this.height = height;
        init();
    }

    private void init() {
        contentView = LayoutInflater.from(context).inflate(layoutRes, null);
        setContentView(contentView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(height);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOutsideTouchable(true);
        setFocusable(true);
//        setAnimationStyle(R.style.PopupAnimation);
        setOnDismissListener(() -> {
            // 将当前窗口的背景恢复为原来的状态
        });
    }

    public void show(View parentView) {
        showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
    }
}
