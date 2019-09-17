package com.hadichallenge.yakson.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.hadichallenge.yakson.R;
import com.hadichallenge.yakson.ui.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openMainActivity();
    }

    public void openMainActivity() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();

            }
        }, 2000);
    }
}
