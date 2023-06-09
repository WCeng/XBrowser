package com.wceng.xbrowser.util;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.jetbrains.annotations.NotNull;

public class PageHelper {

    private final FragmentManager mFragmentManager;
    private final int mContainerId;

    public PageHelper(@NotNull FragmentManager mFragmentManager, int containerId) {
        this.mFragmentManager = mFragmentManager;
        this.mContainerId = containerId;
        Log.e(TAG, "FragmentHelper: "+containerId);
        if(containerId < 0) throw new IllegalArgumentException("ContainerView is not add to rootView");
    }

    public void add(Fragment fragment) {
        if(fragment.isAdded()) return;
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(mContainerId, fragment);
        fragmentTransaction.commit();
    }

    public void replace(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(mContainerId, fragment);
        fragmentTransaction.commit();
    }


    public void show(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    public void hide(Fragment fragment) {
        if(fragment.isHidden()) return;
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.hide(fragment);
        fragmentTransaction.commit();
    }

    public void remove(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

}
