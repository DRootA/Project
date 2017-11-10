package com.example.root.metr.utils;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

    public io.reactivex.Observable<String> startChronometer(long time){
        return io.reactivex.Observable.create(subscriber -> {
            new CountDownTimer(time, 1000) {
                @Override
                public void onTick(long l) {
                    @SuppressLint("SimpleDateFormat")
                    String sTime = new SimpleDateFormat("mm:ss").format(new Date(l));
                    subscriber.onNext(sTime);
                }

                @Override
                public void onFinish() {
                    subscriber.onComplete();
                }
            }.start();
        });
    }

}
