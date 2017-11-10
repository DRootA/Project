package com.example.root.metr.root;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.root.metr.auth.interfaces.LiveCiclePresenter;

public abstract class BaseActivity extends MvpAppCompatActivity {

    protected LiveCiclePresenter liveCiclePresenter;
    public String TAG="TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        liveCiclePresenter.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        liveCiclePresenter.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        liveCiclePresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        liveCiclePresenter.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        liveCiclePresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        liveCiclePresenter.onDestroy();
    }
}
