package com.wceng.xbrowser.widget;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.wceng.xbrowser.util.PageHelper;
import com.wceng.xbrowser.view.activity.BrowserActivity;

import java.util.ArrayList;
import java.util.List;

public abstract class PageContainer extends FrameLayout {

    private final PageHelper mPageHelper;
    private final PageBackStack mPageBackStack;
    private final List<Fragment> mDisplayedPages;

    public PageContainer(@NonNull Context context) {
        super(context);
        setId(View.generateViewId());
        FragmentManager supportFragmentManager = ((BrowserActivity) context).getSupportFragmentManager();
        mPageHelper = new PageHelper(supportFragmentManager, getId());
        mPageBackStack = new PageBackStack();
        mDisplayedPages = new ArrayList<>();
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
        mDisplayedPages.add(fragment);
    }

    public void removePage(Fragment fragment) {
        mPageHelper.remove(fragment);
        mPageBackStack.remove(fragment);
    }

    public void hide() {
        setVisibility(GONE);
    }

    public void show() {
        setVisibility(VISIBLE);
    }

    public boolean contain(Fragment page){
        return mPageBackStack.mPageList.contains(page);
    }

    public void showOnlyPage(Fragment page) {
        List<Fragment> allPages = getAllPages();
        for (Fragment fragment : allPages) {
            if (fragment != page)
                hidePage(fragment);
        }
        showPage(page);
    }

    public Fragment getShowingPage(){
        List<Fragment> allPages = getAllPages();
        for (Fragment page: allPages){
            if(!page.isHidden()){
                return page;
            }
        }
        return null;
    }

    public Fragment getPreDisplayedPage() {
        if(mDisplayedPages.size() < 2){
            return null;
        }else {
            return mDisplayedPages.get(mDisplayedPages.size() - 2);
        }
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
