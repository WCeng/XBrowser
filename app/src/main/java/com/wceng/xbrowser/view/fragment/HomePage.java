package com.wceng.xbrowser.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.wceng.xbrowser.R;
import com.wceng.xbrowser.base.BasePage;
import com.wceng.xbrowser.databinding.FragmentHomeBinding;
import com.wceng.xbrowser.viewmodel.HomeViewModel;
import com.wceng.xbrowser.widget.Window;

public class HomePage extends BasePage {

    private HomeViewModel mViewModel;
    private FragmentHomeBinding mBinding;

    public static HomePage newInstance(Window window) {
        HomePage homePage = new HomePage();
        homePage.setWindow(window);
        return homePage;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        mBinding.constraintLayout.setOnClickListener(v -> {
            getWindow().getWindowController().enterSearchPage();
        });
    }


}