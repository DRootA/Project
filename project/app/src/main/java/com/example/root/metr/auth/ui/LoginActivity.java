package com.example.root.metr.auth.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.root.metr.root.BaseActivity;
import com.example.root.metr.R;
import com.example.root.metr.auth.presentation.AuthPresenter;
import com.example.root.metr.auth.interfaces.ILoginActivity;
import com.example.root.metr.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity implements ILoginActivity {

    @InjectPresenter
    AuthPresenter authPresenter;

    @ProvidePresenter
    AuthPresenter provideAuthPresenter() {
        AuthPresenter auth = new AuthPresenter();
        liveCiclePresenter = auth;
        return auth;
    }

    ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loginBinding.btNext.setOnClickListener(view -> {
            String phone = loginBinding.etEnterPhone.getText().toString();
            authPresenter.phoneValidation(phone);
        });

    }

    @Override
    public void setTextChronometr(String time) {
        loginBinding.tvClock.setText(time);
    }

    @Override
    public void initVisibilitySendInfo(int visibilitySendInfo) {
        loginBinding.tvInfoSend.setVisibility(visibilitySendInfo);
        disableChangeTextListnerEtPhone();
    }

    @Override
    public void initClickSendInfo(boolean needClick) {
        loginBinding.tvClock.setOnClickListener(needClick ? view -> authPresenter.startChronometr() : null);
    }

    @Override
    public void initChangeTextListnerEtPhone() {
        loginBinding.etEnterPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                authPresenter.disableErrorValidPhone();
            }
        });
    }

    @Override
    public void disableChangeTextListnerEtPhone() {
        loginBinding.etEnterPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void setTextInfo(int text) {
        loginBinding.tvInfoSend.setText(text);
    }

    @Override
    public void setHintEtPhone(int hint) {
        loginBinding.etEnterPhone.setHint(hint);
    }

    @Override
    public void clearEtPhone() {
        loginBinding.etEnterPhone.setText("");
    }

    @Override
    public void initClickNextSmsPassword() {
        loginBinding.btNext.setOnClickListener(view -> authPresenter.sendSmsPassword(""));
    }

    @Override
    public void startFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.cstr_login,new AgreementFragment())
                .commit();
    }

}
