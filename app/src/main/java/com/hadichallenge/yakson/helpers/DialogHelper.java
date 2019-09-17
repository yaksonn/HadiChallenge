package com.hadichallenge.yakson.helpers;

import androidx.core.content.ContextCompat;

import com.hadichallenge.yakson.ui.activity.BaseActivity;
import com.kaopiz.kprogresshud.KProgressHUD;


public class DialogHelper {

    private static KProgressHUD spinLoadingDialog;

    public static void showLoadingDialog() {
        if (spinLoadingDialog != null && spinLoadingDialog.isShowing()) {
            return;
        }
        spinLoadingDialog = KProgressHUD.create(BaseActivity.currentActivity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setBackgroundColor(ContextCompat.getColor(BaseActivity.currentActivity, android.R.color.transparent))
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.70f)
                .show();
    }

    public static void hideLoadingDialog() {
        if (spinLoadingDialog != null && spinLoadingDialog.isShowing()) {
            spinLoadingDialog.dismiss();
            spinLoadingDialog = null;
        }
    }

}
