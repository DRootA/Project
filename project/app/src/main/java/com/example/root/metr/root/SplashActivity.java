package com.example.root.metr.root;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.root.metr.R;
import com.example.root.metr.auth.ui.LoginActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //FIXME: исправить задержку на подгрузку данных из сети
        Observable.empty().observeOn(AndroidSchedulers.mainThread())
                .delay(1, TimeUnit.SECONDS)
                .subscribe(s->{},throwable -> {},()->{startActivity(new Intent(this, LoginActivity.class));
                    finish();});
    }
}
