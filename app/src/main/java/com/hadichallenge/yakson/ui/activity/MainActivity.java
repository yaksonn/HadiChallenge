package com.hadichallenge.yakson.ui.activity;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.hadichallenge.yakson.R;
import com.hadichallenge.yakson.helpers.FragmentHelper;
import com.hadichallenge.yakson.ui.fragment.movies.MoviesFragment;
import com.hadichallenge.yakson.ui.fragment.profile.ProfileFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.bottomNavigationView)
    BottomNavigationViewEx bottomNavigationView;

    private FragmentManager fragmentManager;
    private String currentFragmentName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initViews() {
        initBottomNavigation();
    }

    private void initBottomNavigation() {
        fragmentManager = getSupportFragmentManager();
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.enableItemShiftingMode(false);
        bottomNavigationView.setTextVisibility(true);
        bottomNavigationView.setIconSize(24, 24);
        bottomNavigationView.setTextSize(9);
        setBottomNavigationItemSelectListener();
        bottomNavigationView.setCurrentItem(0);
    }

    private void setBottomNavigationItemSelectListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.action_movie:
                        if (currentFragmentName.equals(MoviesFragment.FRAGMENT_NAME)){
                            return false;
                        }
                        FragmentHelper.attachFragmentWithoutBackstack(MoviesFragment.newInstance(), R.id.fragmentContainer);
                        currentFragmentName = MoviesFragment.FRAGMENT_NAME;
                        return true;

                    case R.id.action_profile:

                        if (currentFragmentName.equals(ProfileFragment.FRAGMENT_NAME)){
                            return false;
                        }
                        FragmentHelper.attachFragmentWithoutBackstack(ProfileFragment.newInstance(), R.id.fragmentContainer);
                        currentFragmentName = ProfileFragment.FRAGMENT_NAME;
                        return true;
                }
                return false;
            }
        });
    }


}
