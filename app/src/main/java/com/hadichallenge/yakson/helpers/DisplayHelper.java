package com.hadichallenge.yakson.helpers;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.hadichallenge.yakson.ui.activity.BaseActivity;

public class DisplayHelper {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void changeStatusColor() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            return;
        }
        Window window = BaseActivity.currentActivity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }
}
