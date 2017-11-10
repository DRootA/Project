package com.example.root.metr.auth.presentation;


import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.root.metr.auth.business.AuthInteractor;
import com.example.root.metr.auth.interfaces.LiveCiclePresenter;
import com.example.root.metr.R;
import com.example.root.metr.auth.interfaces.ILoginActivity;
import com.example.root.metr.utils.EditTextValidator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class AuthPresenter extends MvpPresenter<ILoginActivity> implements LiveCiclePresenter {

    private AuthInteractor convertationInteractor;
    private EditTextValidator editTextValidator;

    private String phone;

    Disposable disposable;

    AuthPresenter() {
        convertationInteractor = new AuthInteractor();
        editTextValidator=new EditTextValidator();
    }

    private void firstInitView() {
        getViewState().initClickSendInfo(false);
        getViewState().initVisibilitySendInfo(View.VISIBLE);
        getViewState().setTextInfo(R.string.repeated_request);
        getViewState().setHintEtPhone(R.string.enter_sms_password);
        getViewState().disableChangeTextListnerEtPhone();
        getViewState().clearEtPhone();
        getViewState().initClickNextSmsPassword();
    }

    private void stopChronometr() {
        getViewState().setTextChronometr("Повторная отправка");
        getViewState().initClickSendInfo(true);
        getViewState().initVisibilitySendInfo(View.INVISIBLE);
    }

    void startChronometr() {

        int second = 1000;
        int initialValueTime = 90;

        convertationInteractor
                .startChronometer(initialValueTime * second)
                .doOnSubscribe(disposable -> {
                    this.disposable=disposable;
                    firstInitView();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> getViewState().setTextChronometr(s)
                        , Throwable::printStackTrace,
                        this::stopChronometr);
    }

    void phoneValidation(String phone){
        boolean phoneValid = editTextValidator.phoneValidate(phone);
        if (phoneValid) {
            this.phone=phone;
            startChronometr();
        } else showErrorValidPhone();
    }

    void sendSmsPassword(String smsPassword){
        getViewState().startFragment();
    }

   private void showErrorValidPhone() {
        getViewState().setTextInfo(R.string.error_number_phone);
        getViewState().initVisibilitySendInfo(View.VISIBLE);
        getViewState().initChangeTextListnerEtPhone();
    }

    void disableErrorValidPhone(){
        getViewState().initVisibilitySendInfo(View.INVISIBLE);
        getViewState().disableChangeTextListnerEtPhone();
    }

    @Override
    public void onStop() {
        if(disposable!=null)
        disposable.dispose();
    }
}
