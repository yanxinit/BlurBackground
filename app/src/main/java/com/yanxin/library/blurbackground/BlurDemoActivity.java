package com.yanxin.library.blurbackground;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yanxin.library.library.BlurHandler;

/**
 * Created by YanXin on 2016/1/14.
 */
public class BlurDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur_demo);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        BlurHandler.getInstance().blurBackground(this);
    }

}
