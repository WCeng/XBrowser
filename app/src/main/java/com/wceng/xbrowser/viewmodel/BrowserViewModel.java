package com.wceng.xbrowser.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.button.MaterialButton;

public class BrowserViewModel extends AndroidViewModel {

    private final MutableLiveData<Integer> mWindowNum = new MutableLiveData<>();

    public BrowserViewModel(@NonNull Application application) {
        super(application);
    }

    public void newWindow(View view){
        CharSequence text = ((MaterialButton) view).getText();
        int num = Integer.parseInt((String) text);
        mWindowNum.setValue(++num);
    }

    public MutableLiveData<Integer> getWindowNum() {
        return mWindowNum;
    }
}
