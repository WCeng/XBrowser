package com.wceng.xbrowser.view.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.wceng.xbrowser.R;
import com.wceng.xbrowser.base.BasePage;
import com.wceng.xbrowser.databinding.FragmentSearchBinding;
import com.wceng.xbrowser.util.KeyboardUtils;
import com.wceng.xbrowser.util.UrlUtil;
import com.wceng.xbrowser.viewmodel.SearchViewModel;
import com.wceng.xbrowser.widget.Window;

public class SearchPage extends BasePage {

    FragmentSearchBinding mBinding;
    SearchViewModel mViewModel;

    public static SearchPage newInstance(Window window) {
        SearchPage fragment = new SearchPage();
        fragment.setWindow(window);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        initView();
        inputEventListener();
        initEvent();

    }

    private void initView() {
        KeyboardUtils.showKeyboard(requireContext(), mBinding.inputSearchText);
        mBinding.inputSearchText.setText("https://baidu.com");
    }

    private void inputEventListener() {
        mBinding.inputSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = String.valueOf(s);
                if (text.trim().length() == 0) {
                    mBinding.imageViewCancel.setVisibility(View.GONE);
                    mBinding.imageViewSearch.setImageResource(R.drawable.ic_clean);
                    return;
                } else {
                    mBinding.imageViewCancel.setVisibility(View.VISIBLE);
                }

                if (UrlUtil.isUrl(text)) {
                    mBinding.imageViewSearch.setImageResource(R.drawable.ic_arrow_right);
                } else {
                    mBinding.imageViewSearch.setImageResource(R.drawable.ic_search);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBinding.inputSearchText.setOnEditorActionListener((v, actionId, event) -> {
            String text = v.getText().toString();
            enterWebPage(text);
            return false;
        });

    }

    private void initEvent() {
        mBinding.imageViewCancel.setOnClickListener(v -> {
            mBinding.inputSearchText.setText(null);
        });
        mBinding.imageViewSearch.setOnClickListener(v -> {
            String s = mBinding.inputSearchText.getText().toString();
            enterWebPage(s);
        });
    }

    private void enterWebPage(String searchStr) {
        getWindow().getWindowController().enterWebPage(searchStr);
    }
}
