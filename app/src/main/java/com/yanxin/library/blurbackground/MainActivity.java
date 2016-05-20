package com.yanxin.library.blurbackground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yanxin.library.library.BlurHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openNewActivity(View view) {
        BlurHandler.getInstance().beginBlur(this);
        startActivity(new Intent(this, BlurDemoActivity.class));
    }

    public void openNewFragment(View view) {
        BlurHandler.getInstance().beginBlur(this);
        startActivity(new Intent(this, BlurDemoActivity.class));
    }

}
