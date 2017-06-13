package com.example.yonko.contacts.login;

import com.example.yonko.contacts.BasePresenter;
import com.example.yonko.contacts.BaseView;

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        String getUsername();
        String getPassword();
        void onSuccessfulAuth();
        void setUsernameError(boolean showError);
        void setPasswordError(boolean showError);
    }

    interface Presenter extends BasePresenter {
        void onLoginPressed();
    }
}
