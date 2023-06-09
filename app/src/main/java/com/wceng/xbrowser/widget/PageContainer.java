package com.wceng.xbrowser.widget;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.wceng.xbrowser.util.PageHelper;
import com.wceng.xbrowser.view.activity.BrowserActivity;
import com.wceng.xbrowser.view.fragment.HomePage;

import java.util.ArrayList;
import java.util.List;

public abstract class PageContainer extends FrameLayout {

    private final PageHelper mPageHelper;
    private final PageBackStack mPageBackStack;

    public PageContainer(@NonNull Context context) {
        super(context);
        setId(View.generateViewId());
        FragmentManager supportFragmentManager = ((BrowserActivity) context).getSupportFragmentManager();
        mPageHelper = new PageHelper(supportFragmentManager, getId());
        mPageBackStack = new PageBackStack();
    }

    public void addPage(Fragment fragment) {
        mPageHelper.add(fragment);
        mPageBackStack.add(fragment);
    }

    public List<Fragment> getAllPages(){
        return mPageBackStack.mPageList;
    }

    public void hidePage(Fragment fragment) {
        mPageHelper.hide(fragment);
    }

    public void showPage(Fragment fragment) {
        mPageHelper.show(fragment);
    }

    public void removePage(Fragment fragment) {
        mPageHelper.remove(fragment);
        mPageBackStack.remove(fragment);
    }

    public void replacePage(Fragment fragment) {
        mPageHelper.replace(fragment);
    }

    public void hide() {
        setVisibility(GONE);
    }

    public void show() {
        setVisibility(VISIBLE);
    }

    /**
     * Page堆放栈
     */
    private final static class PageBackStack{
        final List<Fragment> mPageList;

        PageBackStack() {
            this.mPageList = new ArrayList<>();
        }

        void add(Fragment page){
            mPageList.add(page);
        }

        void remove(Fragment page){
            mPageList.remove(page);
        }
    }
}
