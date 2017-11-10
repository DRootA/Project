package com.example.root.metr.auth.business;

import com.example.root.metr.utils.DateTimeUtils;

public class AuthInteractor {

    DateTimeUtils dateTimeUtils;

    public AuthInteractor() {
        dateTimeUtils = new DateTimeUtils();
    }

    public io.reactivex.Observable<String> startChronometer(long time) {
        return dateTimeUtils.startChronometer(time);
    }
}
