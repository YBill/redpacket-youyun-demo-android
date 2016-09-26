package com.youyun.redpacket.login.mvp;

/**
 * Created by 卫彪 on 2016/9/8.
 */
public interface LoginView {

    void loginSuccess();

    void loginFail();

    void showProgress();

    void hideProgress();
}
