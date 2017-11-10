package com.example.root.metr.utils;

import android.text.TextUtils;

public class EditTextValidator {

    public boolean phoneValidate(String phone) {
        boolean empty = TextUtils.isEmpty(phone);
        boolean startWithPlus = phone.startsWith("+");
        return !empty && (startWithPlus && phone.length() == 12 || phone.length() == 11);
    }
}
