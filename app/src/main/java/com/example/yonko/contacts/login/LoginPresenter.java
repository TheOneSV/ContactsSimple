package com.example.yonko.contacts.login;

import java.lang.ref.WeakReference;

public class LoginPresenter implements LoginContract.Presenter {

    private static final String adminUserName = "RCC";
    private static final String adminPassword = "Solutions";

    private WeakReference<LoginContract.View> view;

    public LoginPresenter(LoginContract.View view) {
        this.view = new WeakReference<>(view);
        this.view.get().setPresenter(this);
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
    }

    @Override
    public void onLoginPressed() {
        String userName = view.get().getUsername();
        String userPassword = view.get().getPassword();

        boolean isUsernameValid = adminUserName.equals(userName);
        boolean isPasswordValid = adminPassword.equals(userPassword);

        if (!isUsernameValid) {
            view.get().setUsernameError(true);
        }
        if (!isPasswordValid) {
            view.get().setPasswordError(true);
        }

        if(isUsernameValid && isPasswordValid) {
            view.get().onSuccessfulAuth();
        }
    }
}
