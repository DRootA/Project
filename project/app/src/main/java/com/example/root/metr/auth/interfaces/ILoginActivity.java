package com.example.root.metr.auth.interfaces;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface ILoginActivity extends MvpView {
    void setTextChronometr(String time);
    void initVisibilitySendInfo(int visibilitySendInfo);
    void initClickSendInfo(boolean needClick);
    void initChangeTextListnerEtPhone();
    void disableChangeTextListnerEtPhone();
    void setTextInfo(int text);
    void setHintEtPhone(int hint);
    void clearEtPhone();
    void initClickNextSmsPassword();
    void startFragment();
}
