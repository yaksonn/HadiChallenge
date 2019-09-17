package com.hadichallenge.yakson;

import android.app.Application;

import com.hadichallenge.yakson.ui.activity.BaseActivity;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class HadiLiveChallengeApp extends Application {

    private static HadiLiveChallengeApp instance;
    private BaseActivity currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initPicasso();
    }
    public static HadiLiveChallengeApp getInstance() {
        return instance;
    }

    public BaseActivity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(BaseActivity activity) {
        currentActivity = activity;
    }

    private void initPicasso() {
        Picasso picasso = new Picasso.Builder(this).downloader(new OkHttp3Downloader(getCacheDir())).build();
        Picasso.setSingletonInstance(picasso);
    }
}
