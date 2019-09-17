package com.hadichallenge.yakson.helpers;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.hadichallenge.yakson.ui.base.BaseActivity;

public class FragmentHelper {
    public static void attachFragmentWithoutBackstack(Fragment newFragment, int containerId) {
        FragmentTransaction fragmentTransaction = BaseActivity.currentActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerId, newFragment);
        fragmentTransaction.commit();
    }
}
