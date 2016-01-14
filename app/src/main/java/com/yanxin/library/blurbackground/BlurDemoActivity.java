package com.yanxin.library.blurbackground;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yanxin.library.library.BlurCompleteListener;
import com.yanxin.library.library.BlurHandler;

/**
 * Created by YanXin on 2016/1/14.
 */
public class BlurDemoActivity extends AppCompatActivity implements BlurCompleteListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur_demo);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateBackground();
        BlurHandler.getInstance().addBlurCompleteListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BlurHandler.getInstance().removeBlurCompleteListener(this);
    }

    @Override
    public void onComplete() {
        updateBackground();
    }

    private void updateBackground() {
        getWindow().getDecorView().getRootView().setBackgroundDrawable(new BitmapDrawable(BlurHandler.getInstance().getImage()));
    }
}
