package com.yanxin.blurbackground.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.yanxin.blurbackground.library.BlurHandler;

/**
 * Created by YanXin on 2016/5/20.
 */
public class BlurDemoFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BlurHandler.getInstance().blurBackground(this);
    }
}
